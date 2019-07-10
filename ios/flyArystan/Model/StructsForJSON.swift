//
//  StructsForJSON.swift
//  flyArystan
//
//  Created by Абылайхан on 7/10/19.
//  Copyright © 2019 Абылайхан. All rights reserved.
//

import Foundation
//Registration
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
struct fStruct: Codable {
    var status: String
}

//Search
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
//Buy
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

//Check
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

//Help
struct structForHelp: Codable {
    var name: String
    var mail: String
    var ticket_number: String
    var message: String
}

//Login

struct structForLogin: Codable {
    var mobile: String
    var password: String
}

struct structFromLogin: Codable {
    var status: String
}

//Register
struct structForOR: Codable{
    var number: String
    var name: String
    var surname: String
}



