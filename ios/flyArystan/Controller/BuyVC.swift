//
//  BuyVC.swift
//  flyArystan
//
//  Created by Абылайхан on 7/7/19.
//  Copyright © 2019 Абылайхан. All rights reserved.
//

import UIKit
struct structForBuy: Codable {
    var to: String
    var from: String
    var gender: Int
    var name: String
    var surname: String
    var day: String
    var month: String
    var year: String
    var nation: String
    var doctype: String
    var docnumber: String
    var tel: String
    var email: String
    var carduser: String
    var cardnum: String
    var cardMonth: String
    var cardYear: String
    var cvv: String
}
class BuyVC: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {

    
    @IBOutlet weak var genderPicker: UIPickerView!
    let genders = ["Мужчина", "Женщина"]
    
    @IBOutlet weak var nameField: UITextField!
    
    @IBOutlet weak var surnameField: UITextField!
    @IBOutlet weak var nationPicker: UIPickerView!
    let nations = ["Kazakh"]
    
    @IBOutlet weak var birthPicker: UIDatePicker!
    
    @IBOutlet weak var doctypePicker: UIPickerView!
    let doctypes = ["Удостоверение личности"]
    
    @IBOutlet weak var docnumberField: UITextField!
    
    @IBOutlet weak var telField: UITextField!
    
    @IBOutlet weak var emailField: UITextField!
    
    @IBOutlet weak var carduserField: UITextField!
    
    
    @IBOutlet weak var cardnumField: UITextField!
    
    @IBOutlet weak var cardmonthField: UITextField!
    @IBOutlet weak var cardyearField: UITextField!
    @IBOutlet weak var cvvField: UITextField!
    
    var to: String!
    var from: String!
    override func viewDidLoad() {
        super.viewDidLoad()
        nationPicker.delegate = self
        nationPicker.dataSource = self
        
        genderPicker.delegate = self
        genderPicker.dataSource = self
        
        doctypePicker.delegate = self
        doctypePicker.dataSource = self

        // Do any additional setup after loading the view.
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        switch pickerView.tag {
        case 1:
            return genders.count
        case 2:
            return nations.count
        case 3:
            return doctypes.count
        default:
            return 0
        }
    }
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        switch pickerView.tag {
        case 1:
            return genders[row]
        case 2:
            return nations[row]
        case 3:
            return doctypes[row]
        default:
            return ""
        }
    }
    
    
    @IBAction func buyTapped(_ sender: Any) {
        guard let name = nameField.text,
        let surname = surnameField.text,
        let docnumber = docnumberField.text,
        let tel = telField.text,
        let email = emailField.text,
        let carduser = carduserField.text?.uppercased(),
        let cardnum = cardnumField.text,
        let cardMonth = cardmonthField.text,
        let cardYear = cardyearField.text,
            let cvv = cvvField.text else {
                print("Error in constructing buy structure")
                return
        }
        var gender: Int!
        switch genders[genderPicker.selectedRow(inComponent: 0)] {
        case "Женщина":
            gender = 2
        default:
            gender = 1
        }
        
        let fmt = DateFormatter()
        fmt.dateFormat = "dd/MM/yyyy"
        let dateString = fmt.string(from: birthPicker.date)
        let day = "\(dateString[0])\(dateString[1])"
        let month = "\(dateString[3])\(dateString[4])"
        let year = "\(dateString.substring(fromIndex: 6))"
        
        var doctype: String!
        
        switch doctypes[doctypePicker.selectedRow(inComponent: 0)] {
        case "Удостоверение личности":
            doctype = "natID"
        default:
            doctype = "natID"
        }
        
        var nation: String!
        
        switch nations[nationPicker.selectedRow(inComponent: 0)] {
        case "Kazakh":
            nation = "KZ"
        default:
            nation = "KZ"
        }
        
        let struct1 = structForBuy(to: "Тараз", from: "Алматы", gender: 1, name: "Adilbek", surname: "Baktiyar", day: "25", month: "9", year: "1999", nation: "KZ", doctype: "natID", docnumber: "040178888", tel: "77759527800", email: "baktiyarov_adilbek@mail.ru", carduser: "AMANTAY ORYNBAYEV", cardnum: "5578342725090499", cardMonth: "09", cardYear: "22", cvv: "320")
        print(struct1.name)
        print(struct1.carduser)
        
        do {
            let jsonData = try JSONEncoder().encode(struct1)
            var urlRequest = URLRequest(url: URL(string: "\(BASE_URL)\(BUY)")!) // Configure in a right way
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
                if let data1 = data, let string = String(data: data1, encoding: .utf8) {
                    print(string)
                }
                
            }
            task.resume()
            
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

extension String {
    
    var length: Int {
        return count
    }
    
    subscript (i: Int) -> String {
        return self[i ..< i + 1]
    }
    
    func substring(fromIndex: Int) -> String {
        return self[min(fromIndex, length) ..< length]
    }
    
    func substring(toIndex: Int) -> String {
        return self[0 ..< max(0, toIndex)]
    }
    
    subscript (r: Range<Int>) -> String {
        let range = Range(uncheckedBounds: (lower: max(0, min(length, r.lowerBound)),
                                            upper: min(length, max(0, r.upperBound))))
        let start = index(startIndex, offsetBy: range.lowerBound)
        let end = index(start, offsetBy: range.upperBound - range.lowerBound)
        return String(self[start ..< end])
    }
    
}
