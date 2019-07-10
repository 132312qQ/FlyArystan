//
//  HelpVC.swift
//  flyArystan
//
//  Created by Абылайхан on 7/7/19.
//  Copyright © 2019 Абылайхан. All rights reserved.
//

import UIKit
import RSKGrowingTextView

class HelpVC: UIViewController {

    
    @IBOutlet weak var messageTextView: RSKGrowingTextView!
    @IBOutlet weak var tickec_numberField: UITextField!
    @IBOutlet weak var mailField: UITextField!
    @IBOutlet weak var nameField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()

        tickec_numberField.borderStyle = .none
        mailField.borderStyle = .none
        nameField.borderStyle = .none
        messageTextView.placeholder = "Message"
        messageTextView.placeholderColor = UIColor.red
        
        // Do any additional setup after loading the view.
    }
    @IBAction func butonTapped(_ sender: Any) {
        
        guard let name = nameField.text,
        let mail = mailField.text,
            let ticket_number = tickec_numberField.text,
        let message = messageTextView.text
        else {
                return
        }
        let struct1 = structForHelp(name: name, mail: mail, ticket_number: ticket_number, message: message)
        do {
            let jsonData = try JSONEncoder().encode(struct1)
            var urlRequest = URLRequest(url: URL(string: "\(BASE_URL)\(CONTACTUS)")!) // Configure in a right way
            urlRequest.httpMethod = "POST"
            urlRequest.addValue("application/json", forHTTPHeaderField: "Content-Type")
            let task = URLSession.shared.uploadTask(with: urlRequest, from: jsonData)
            task.resume()
            DispatchQueue.main.async {
                let alert = UIAlertController(title: "Status", message: "Спасибо за отзыв, мы постараемся связаться", preferredStyle: .alert)
                alert.addAction(UIAlertAction(title: "Отмена", style: .cancel, handler: nil))
                self.present(alert, animated: true, completion: nil)
                self.nameField.text = ""
                self.mailField.text = ""
                self.tickec_numberField.text = ""
                self.messageTextView.text = ""
                
                
                
            }
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
