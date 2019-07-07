//
//  CheckVC.swift
//  flyArystan
//
//  Created by Абылайхан on 7/7/19.
//  Copyright © 2019 Абылайхан. All rights reserved.
//

import UIKit
struct structForCheckStatus: Codable {
    var from: String
    var to: String
    var day: String
}

struct FromCheckStatus: Codable {
    var flight: String
    var from1: String
    var from2: String
    var to1: String
    var to2: String
    var message: String
}
class CheckVC: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {

let days = ["yesterday", "today", "tomorrow"]
    let cities = ["Алматы": "ALA", "Павлодар": "PWQ","Уральск": "URA","Караганда": "KGF","Тараз": "DMB", "Нур-Султан": "TSE", "Шымкент": "CIT"]
    override func viewDidLoad() {
        super.viewDidLoad()
        datePicker.delegate = self
        datePicker.dataSource = self
        // Do any additional setup after loading the view.
        
    }
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return days.count
    }
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return days[row]
    }
    @IBOutlet weak var datePicker: UIPickerView!
    
    @IBOutlet weak var fromField: UITextField!
    
    @IBOutlet weak var toField: UITextField!
    
     @IBAction func checkTapped(_ sender: Any) {
        guard let from = fromField.text,
            let to = toField.text else {
                return
        }
        let day = days[datePicker.selectedRow(inComponent: 0)]
        let struct1 = structForCheckStatus(from: cities[from]!, to: cities[to]!, day: day)
        do {
            let jsonData = try JSONEncoder().encode(struct1)
            var urlRequest = URLRequest(url: URL(string: "\(BASE_URL)\(STATUS)")!) // Configure in a right way
            urlRequest.httpMethod = "POST"
            urlRequest.addValue("application/json", forHTTPHeaderField: "Content-Type")
            
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
                if let data = data {
                    let data1 = try! JSONDecoder().decode(FromCheckStatus.self, from: data)
                    
                    DispatchQueue.main.async {
                        let alert = UIAlertController(title: "Status", message: "Flight:  \(data1.flight)\nФактическое: \(data1.from1)\nЗапланированное: \(data1.from2)\nОжидаемое: \(data1.to1)\nЗапланированное: \(data1.to2)\nСтатус: \(data1.message)", preferredStyle: .alert)
                        alert.addAction(UIAlertAction(title: "Отмена", style: .cancel, handler: nil))
                        
                        self.present(alert, animated: true, completion: nil)
                        
                        
                    }
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
