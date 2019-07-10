//
//  PersonToRegister.swift
//  flyArystan
//
//  Created by Абылайхан on 7/10/19.
//  Copyright © 2019 Абылайхан. All rights reserved.
//

import UIKit

class PersonToRegister {
    private(set) var person: rStruct

    init(person: rStruct){
        self.person = person
    }
    func register(vc: RegistrationVC) {
        
        var status = ""
        do {
            let jsonData = try JSONEncoder().encode(person)
            var urlRequest = URLRequest(url: URL(string: "\(BASE_URL)\(REGISTER)")!) // Configure in a right way
            urlRequest.httpMethod = "POST"
            urlRequest.addValue("application/json", forHTTPHeaderField: "Content-Type")
            
            let task = URLSession.shared.uploadTask(with: urlRequest, from: jsonData){ (data, response, error) in
                let data1 = try! JSONDecoder().decode(fStruct.self, from: data!)
                status = data1.status
                DispatchQueue.main.async {
                    let alert = UIAlertController(title: "Status", message: "Status: \(status)", preferredStyle: .alert)
                    alert.addAction(UIAlertAction(title: "Отмена", style: .cancel, handler: nil))
                    vc.present(alert, animated: true)
                    
                }
                    
                    
                
            }
            task.resume()
            
        } catch {
            print(error.localizedDescription)
        }
    }
    
}
