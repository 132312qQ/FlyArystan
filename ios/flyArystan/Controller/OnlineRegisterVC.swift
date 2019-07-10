//
//  OnlineRegisterVC.swift
//  flyArystan
//
//  Created by Абылайхан on 7/7/19.
//  Copyright © 2019 Абылайхан. All rights reserved.
//

import UIKit
import TextFieldEffects

class OnlineRegisterVC: UIViewController {

    @IBOutlet weak var numberF: UITextField!
    
    @IBOutlet weak var nameF: UITextField!
    
    @IBOutlet weak var surnameF: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        numberF.borderStyle = .none
        nameF.borderStyle = .none
        surnameF.borderStyle = .none
        
        // Do any additional setup after loading the view.
    }
    

    @IBAction func buttonTapped(_ sender: Any) {
        guard let number = numberF.text,
        let name = nameF.text,
            let surname = surnameF.text else {
                return
        }
        let struct1 = structForOR(number: number, name: name, surname: surname)
        do {
            let jsonData = try JSONEncoder().encode(struct1)
            var urlRequest = URLRequest(url: URL(string: "\(BASE_URL)\(ONLINEREGISTER)")!) // Configure in a right way
            urlRequest.httpMethod = "POST"
            urlRequest.addValue("application/json", forHTTPHeaderField: "Content-Type")
            let task = URLSession.shared.uploadTask(with: urlRequest, from: jsonData){
                (data, response, error) in
                if let data = data {
                    let data1 = try! JSONDecoder().decode(structFromLogin.self, from: data)

                    DispatchQueue.main.async {
                        let alert = UIAlertController(title: "Status", message: "Status: \(data1.status)", preferredStyle: .alert)
                        alert.addAction(UIAlertAction(title: "Отмена", style: .cancel, handler: nil))
                        
                        self.present(alert, animated: true, completion: nil)
                        
                        
                    }                }
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
