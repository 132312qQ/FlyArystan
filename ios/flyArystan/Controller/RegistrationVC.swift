//
//  RegistrationVC.swift
//  flyArystan
//
//  Created by Абылайхан on 7/6/19.
//  Copyright © 2019 Абылайхан. All rights reserved.
//

import UIKit
struct rStruct: Codable {
    var name: String
    var surname: String
    var status: String
    var lang: String
    var birth: String
    var country: String
    var passport: String
    var expireDate: String
    var mail: String
    var mobile: String
    var password1: String
    var password2: String
    var national_id: String
}

class RegistrationVC: UIViewController,UIPickerViewDelegate, UIPickerViewDataSource {
    @IBOutlet weak var statusPicker: UIPickerView!
    @IBOutlet weak var langPicker: UIPickerView!
    @IBOutlet weak var countryPicker: UIPickerView!
    @IBOutlet weak var national_id: UITextField!
    
    @IBOutlet weak var name: UITextField!
    
    @IBOutlet weak var birthDatePicker: UIDatePicker!
    
    @IBOutlet weak var issuePicker: UIDatePicker!
    @IBOutlet weak var confirmPassword: UITextField!
    @IBOutlet weak var password: UITextField!
    @IBOutlet weak var telNumber: UITextField!
    @IBOutlet weak var email: UITextField!
    @IBOutlet weak var idPassport: UITextField!
    @IBOutlet weak var surName: UITextField!
    var statuses = ["Господин", "Госпожа"]
    var langs = ["Qazaq", "Русский", "English"]
    var contries = ["Kazakhstan", "USA", "UK"]
    override func viewDidLoad() {
        super.viewDidLoad()
        statusPicker.delegate = self
        langPicker.delegate = self
        countryPicker.delegate = self
        
        statusPicker.dataSource = self
        langPicker.dataSource = self
        countryPicker.dataSource = self
        print("LOOOK")
        print(statusPicker.selectedRow(inComponent: 0))
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        switch pickerView.tag {
        case 1:
            return statuses.count
        case 2:
            return langs.count
        case 3:
            return contries.count
        default:
            return 0
        }
    }
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        switch pickerView.tag {
        case 1:
            return statuses[row]
        case 2:
            return langs[row]
        case 3:
            return contries[row]
        default:
            return ""
        }
    }
    
    
    
    
    @IBAction func registered(_ sender: Any) {
        guard let name = name.text,
            let surName = surName.text,
           let idPassport = idPassport.text,
            let email = email.text,
            let telNumber = telNumber.text,
            let password1 = password.text,
            let password2 = confirmPassword.text,
            let national_id = national_id.text else {
                print("Some field are wrong")
                return
        }
            
       
            let fmt = DateFormatter()
            fmt.dateFormat = "dd/MM/yyyy"
            let birth = fmt.string(from: birthDatePicker.date)
            let issue = fmt.string(from: issuePicker.date)
            let struct1 = rStruct(name: name,
                                  surname: surName,
                                  status: statuses[statusPicker.selectedRow(inComponent: 0)],
                                  lang: langs[langPicker.selectedRow(inComponent: 0)],
                                  birth: birth,
                                  country: contries[countryPicker.selectedRow(inComponent: 0)],
                                  passport: idPassport,
                                  expireDate: issue,
                                  mail: email,
                                  mobile: telNumber,
                                  password1: password1,
                                  password2: password2,
                                  national_id: national_id)
            do {
                let jsonData = try JSONEncoder().encode(struct1)
                var urlRequest = URLRequest(url: URL(string: "\(BASE_URL)\(LIST_URL)")!) // Configure in a right way
                urlRequest.httpMethod = "POST"
                
                let task = URLSession.shared.uploadTask(with: urlRequest, from: jsonData) { (data, response, error) in
                    //
                    if let error = error {
                        print("!!Error!!:\(error.localizedDescription)")
                        return
                    }
                    //
                    if let response = response as? HTTPURLResponse {
                        print("!!Response!!: \(response.allHeaderFields), StatusCode: \(response.statusCode)")
                    } else {
                        print("!!Error in response!!)")
                        return
                    }
                    //
                    if let data = data, let string = String(data: data, encoding: .utf8) {
                        print(string)
                    }
                    
                }
                //task.resume()
                print(struct1.birth)
            } catch {
                print(error.localizedDescription)
            }

        
    }
    
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
