//
//  SearchVC.swift
//  flyArystan
//
//  Created by Абылайхан on 7/7/19.
//  Copyright © 2019 Абылайхан. All rights reserved.
//

import UIKit
struct structFS: Codable {
    var from: String
    var to: String
    //var date: String
}
struct structFromServer: Codable {
    var price: String
    var timeGo: String
    var timeOut: String
}
class SearchVC: UIViewController {

    @IBOutlet weak var from: UITextField!
    @IBOutlet weak var toWhere: UITextField!
    @IBOutlet weak var date: UIDatePicker!
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    @IBAction func searchPresses(_ sender: Any) {
        guard let from = from.text,
            let toWhere = toWhere.text else {
                print("Error in constructing the structure for searching")
                return
        }
        let fmt = DateFormatter()
        fmt.dateFormat = "dd/MM/yyyy"
        let date1 = fmt.string(from: date.date )
        
        let struct1 = structFS(from: from, to: toWhere)
        
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
                if let data1 = data, let string = String(data: data1, encoding: .utf8) {
                    do{
                        let list = try JSONDecoder().decode(structFromServer.self, from: data1)
                        
                        DispatchQueue.main.async {
                            let alert = UIAlertController(title: "Ticket", message: "\(list.price)\n\(list.timeGo)\n\(list.timeOut)", preferredStyle: .alert)
                            alert.addAction(UIAlertAction(title: "Отмена", style: .cancel, handler: nil))
                            alert.addAction(UIAlertAction(title: "Купить", style: .default, handler: { (UIAlertAction) in
                                self.performSegue(withIdentifier: "segueId1", sender: struct1)
                            }))
                            self.present(alert, animated: true, completion: nil)
                            

                        }
                    } catch {
                        print(error.localizedDescription)
                    }
                    
                    
                }
                
            }
            task.resume()
            
        } catch {
            print(error.localizedDescription)
        }
    }
    
    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
        if let destination = segue.destination as? BuyVC, let struct1 = sender as? structFS{
            destination.to = struct1.to
            destination.from = struct1.from
        }
    }
    

}