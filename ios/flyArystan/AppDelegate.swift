//
//  AppDelegate.swift
//  flyArystan
//
//  Created by Абылайхан on 7/6/19.
//  Copyright © 2019 Абылайхан. All rights reserved.
//

import UIKit

struct RequestForList: Codable {
    var returnDate: String
    var tripType: String
    var language: String
    var dateFormat: String
    var depPort: String
    var arrPort: String
    var adult: Int
    var child: Int
    var infant: Int
    var departureDate: String
}

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?


    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        
        var urlRequest = URLRequest(url: URL(string: "\(BASE_URL)\(LIST_URL)")!)
        urlRequest.httpMethod = "POST"
        urlRequest.addValue("booking.flyarystan.com", forHTTPHeaderField: "authority")
        urlRequest.addValue("JSESSIONID=01B5C41A195E4886548BA5ADD104FC9E; visid_incap_1880816=RJ+j/DwxQnOBphoE7JQwqNNJIF0AAAAAQUIPAAAAAABDoH8QkNbxpsgT9sikszcx; ARRAffinity=97c5cfc22a7da2f0dca3ab95c141bfb584c3e3900dc0195dce096b77715cecb4; ckPAXpersist=!RYJSDhhr5lsVM5ciEP5Ysl9+8I1IRAC6PZcLAfs6Q1EdqolYUP9qGy3OQP2E+bvuLoKgl9diyXQcvYs=; GCLB=CIL05u-I3LKi9QE; _ym_uid=1562397145779409573; _ym_d=1562397145; _ga=GA1.2.1608038077.1562397145; _gid=GA1.2.1696850193.1562397145; _ym_isad=2; _fbp=fb.1.1562397146006.185265813; JSESSIONID=2A4C236BFC1B4BAED41261B293FD0941; _ym_visorc_53644852=w; incap_ses_584_1880816=AW/PBpz13AMnSVF0HckaCGllIF0AAAAA7MARdPjPGONN+Gde0VTCtQ==; incap_ses_536_1880816=QYyqWMJAUzg8i6jAqUdwBxRnIF0AAAAACoGE7rth+BB09j8s8Py3vQ==", forHTTPHeaderField: "cookie")
        //urlRequest.addValue("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 YaBrowser/19.6.0.1583 Yowser/2.5 Safari/537.36", forHTTPHeaderField: "user-agent")
        
        
        let reqFL = RequestForList(returnDate: "25/7/2019", tripType: "ROUND_TRIP", language: "ru", dateFormat: "dd/MM/yyyy", depPort: "ALA", arrPort: "KGF", adult: 1, child: 0, infant: 0, departureDate: "18/7/2019")
        let jData: Data!
        do{
            jData = try? JSONEncoder().encode(reqFL)
        } catch {
            print(error.localizedDescription)
        }
        let task = URLSession.shared.uploadTask(with: urlRequest, from: jData) { (data, response, error) in
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
        
        
        
        return true
    }

    func applicationWillResignActive(_ application: UIApplication) {
        // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
        // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
        // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
        // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
        // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
    }

    func applicationWillTerminate(_ application: UIApplication) {
        // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
    }


}

