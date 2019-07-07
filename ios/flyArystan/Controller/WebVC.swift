//
//  WebVC.swift
//  flyArystan
//
//  Created by Абылайхан on 7/7/19.
//  Copyright © 2019 Абылайхан. All rights reserved.
//

import UIKit

class WebVC: UIViewController {

    var url: URL!
    @IBOutlet weak var webView: UIWebView!
    override func viewDidLoad() {
        super.viewDidLoad()
        webView.loadRequest(URLRequest(url: url))

        // Do any additional setup after loading the view.
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
