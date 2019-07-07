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

    @IBOutlet weak var toWhere: UIPickerView!
    @IBOutlet weak var from: UIPickerView!
    
    let cities2 =  ["Алматы", "Павлодар","Уральск","Караганда","Тараз", "Нур-Султан", "Шымкент"]
    
    let days = ["yesterday", "today", "tomorrow"]
    
    let cities = ["Алматы": "ALA", "Павлодар": "PWQ","Уральск": "URA","Караганда": "KGF","Тараз": "DMB", "Нур-Султан": "TSE", "Шымкент": "CIT"]
    override func viewDidLoad() {
        super.viewDidLoad()
        datePicker.delegate = self
        datePicker.dataSource = self
        
        from.delegate = self
        from.dataSource = self
        
        toWhere.delegate = self
        toWhere.dataSource = self
        // Do any additional setup after loading the view.
        
    }
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        switch pickerView.tag {
        case 1:
            return cities2.count
        case 2:
            return days.count
        default:
            return 0
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        
        switch pickerView.tag {
        case 1:
            return cities2[row]
        case 2:
            return days[row]
        default:
            return ""
        }
    }
    @IBOutlet weak var datePicker: UIPickerView!
    
    
    
     @IBAction func checkTapped(_ sender: Any) {
        let from = cities[cities2[self.from.selectedRow(inComponent: 0)]]
            let to = cities[cities2[self.toWhere.selectedRow(inComponent: 0)]]
        let day = days[datePicker.selectedRow(inComponent: 0)]
        let struct1 = structForCheckStatus(from: from!, to: to!, day: day)
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
