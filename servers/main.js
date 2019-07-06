var express = require('express'); 
var app = express(); 
var cors = require('cors')

app.use(cors())
app.post('/price', getPrice); 
app.post('/buy', readyBuy); 

function getPrice(req, res) { 
    const puppeteer = require('puppeteer');
    (async () => {
    const browser = await puppeteer.launch({headless: false, args: ['--disable-dev-shm-usage']});
    const page = await browser.newPage();
    
    await page.setViewport()
   
    try{
        await page.goto('https://flyarystan.com/',{ timeout: 120000, waitUntil: 'networkidle0', waitUntil: "load" });
        var inputData = "#depDate"
        var findBtn   = "#flightTab > div > div.col-md-1.padding0.flightsearch > button";

        var cityto = req.query.to;
        var cityfrom = req.query.from;

        var addCity = "3";
        if(cityto == "Караганда"){
            addCity = "3";
        }
        if(cityto == "Тараз"){
            addCity = "5";
        }
        

        var inputFrom = "#depPortInput";
        await page.click(inputFrom)
        var fromCity = "#depPortsCust > a:nth-child(" + addCity + ")"
        await page.click(fromCity)

        var inputTo = "#arrPortInput";
        await page.click(inputTo)
        var toCity = "#arrPortsCust > a"
        await page.click(toCity)
        
        let oneWay = "#oneway";
        await page.click(oneWay);
        let tempDate = "#datepickerflight > div.datepicker--content > div > div.datepicker--cells.datepicker--cells-days > div:nth-child(23)";
        await page.click(tempDate);
        
        await page.evaluate((findBtn) => document.querySelector(findBtn).click(), findBtn);

        
        // @@@@@@@@@@@@@@@@@@@@@@ next page
        const priceTag = 'div > div.mobile-price.col-xs-4.col-md-12.col-sm-12.col-lg-12 > span';         
        await page.waitForSelector(priceTag);         
        const priceSend = await page.$eval(
            priceTag, priceTag => priceTag.innerHTML
        );
        console.log(priceSend)
        
        const timeGo = '#flightID_0_1_0 > div.info.col-lg-6.col-md-6.col-sm-6.col-xs-12 > div.col-lg-5.col-md-5.col-sm-5.col-xs-5.departure-info > span';         
        await page.waitForSelector(timeGo);         
        const timeGoSend = await page.$eval(
            timeGo, timeGo => timeGo.innerHTML
        );
        console.log(timeGoSend)

        var timeOut = '#flightID_0_1_0 > div.info.col-lg-6.col-md-6.col-sm-6.col-xs-12 > div.col-lg-5.col-md-5.col-sm-5.col-xs-5.arrival-info > span';         
        await page.waitForSelector(timeOut);         
        var timeOutSend = await page.$eval(
            timeOut, timeOut => timeOut.innerHTML
        );
        timeOutSend = timeOutSend.replace(/\s/g, "")
        console.log(timeOutSend)
    
        var result = { "price" : priceSend, "timeGo" : timeGoSend, "timeOut" : timeOutSend };
        res.json(result);
        //await browser.close();
    }
    catch(error){
        browser.close();
    }
    })();
}

function readyBuy(req, res) { 
    const puppeteer = require('puppeteer');
    (async () => {
    const browser = await puppeteer.launch({headless: false, args: ['--disable-dev-shm-usage']});
    const page = await browser.newPage();
    
    await page.setViewport({ width: 1600, height: 800 })
   
    try{
        await page.goto('https://flyarystan.com/',{ timeout: 120000, waitUntil: 'networkidle0', waitUntil: "load" });
        var inputData = "#depDate"
        var findBtn   = "#flightTab > div > div.col-md-1.padding0.flightsearch > button";

        var cityto = req.query.to;
        var cityfrom = req.query.from;

        var addCity = "3";
        if(cityto == "Караганда"){
            addCity = "3";
        }
        if(cityto == "Тараз"){
            addCity = "5";
        }
        

        var inputFrom = "#depPortInput";
        await page.click(inputFrom)
        var fromCity = "#depPortsCust > a:nth-child(" + addCity + ")"
        await page.click(fromCity)

        var inputTo = "#arrPortInput";
        await page.click(inputTo)
        var toCity = "#arrPortsCust > a"
        await page.click(toCity)
        
        let oneWay = "#oneway";
        await page.click(oneWay);
        let tempDate = "#datepickerflight > div.datepicker--content > div > div.datepicker--cells.datepicker--cells-days > div:nth-child(23)";
        await page.click(tempDate);
        
        await page.evaluate((findBtn) => document.querySelector(findBtn).click(), findBtn);

        
        // @@@@@@@@@@@@@@@@@@@@@@ next page
        const priceTag = 'div > div.mobile-price.col-xs-4.col-md-12.col-sm-12.col-lg-12 > span';         
        await page.waitForSelector(priceTag);         
        const priceSend = await page.$eval(
            priceTag, priceTag => priceTag.innerHTML
        );
        console.log(priceSend)
        
        const timeGo = '#flightID_0_1_0 > div.info.col-lg-6.col-md-6.col-sm-6.col-xs-12 > div.col-lg-5.col-md-5.col-sm-5.col-xs-5.departure-info > span';         
        await page.waitForSelector(timeGo);         
        const timeGoSend = await page.$eval(
            timeGo, timeGo => timeGo.innerHTML
        );
        console.log(timeGoSend)

        var timeOut = '#flightID_0_1_0 > div.info.col-lg-6.col-md-6.col-sm-6.col-xs-12 > div.col-lg-5.col-md-5.col-sm-5.col-xs-5.arrival-info > span';         
        await page.waitForSelector(timeOut);         
        var timeOutSend = await page.$eval(
            timeOut, timeOut => timeOut.innerHTML
        );
        timeOutSend = timeOutSend.replace(/\s/g, "")
        console.log(timeOutSend)
    
        var result = { "price" : priceSend, "timeGo" : timeGoSend, "timeOut" : timeOutSend };
        res.json(result);

        var buyBtn = "div > div.price-button.col-lg-12.col-md-12.col-sm-12.col-xs-4 > button"
        await page.evaluate((buyBtn) => document.querySelector(buyBtn).click(), buyBtn);
        var clickStandard = "div > div.bundle-element-body.pricing"
        await page.evaluate((clickStandard) => document.querySelector(clickStandard).click(), clickStandard);
        var clickContinue = "#basket > div.row > div.col-lg-12.col-md-12.col-sm-12.col-xs-12.basket-summary > div.col-lg-7.col-md-12.col-sm-12.col-xs-12.summary-right-area > div.col-lg-12.col-md-12.col-sm-12.col-xs-12.without-membership-info > span > input"
        await page.waitForSelector(clickContinue);
        await page.evaluate((clickContinue) => document.querySelector(clickContinue).click(), clickContinue);
        
        // buy info from UI
        var gender1 = "#gender1"
        await page.waitForSelector(gender1)
        // document.querySelector(gender1).selectedIndex = 1; // input from  
        // if male  = 1
        // if women = 2        
        await page.$eval(gender1, (el, value) => el.selectedIndex = value, req.query.gender);

        var name1 = "#name1"
        await page.$eval(name1, (el, value) => el.value = value, req.query.name);

        var surname1 = "#surname1"
        await page.$eval(surname1, (el, value) => el.value = value, req.query.surname);

        var day1 = "#bday_day_1"    
        await page.$eval(day1, (el, value) => el.selectedIndex = value, req.query.day);

        var month1 = "#bday_month_1"    
        await page.$eval(month1, (el, value) => el.selectedIndex = value, req.query.month);

        var year1 = "#bday_year_1"
        await page.$eval(year1, (el, value) => el.value = value, req.query.year);
        
        var nationality1 = "#nationality1"
        await page.$eval(nationality1, (el, value) => el.value = value, req.query.nation);

        var documentType1 = "#documentType1"
        await page.$eval(documentType1, (el, value) => el.value = value, req.query.doctype);


        var againName =  "#contact_name0"
        await page.$eval(againName, (el, value) => el.value = value, req.query.name);

        var againSurname = "#contact_surname0"
        await page.$eval(againSurname, (el, value) => el.value = value, req.query.surname);

        /* lol kek
        // var documentNumber = "#natId"
        // await page.waitFor(2500)
        // await page.$eval(documentNumber, (el, value) => el.value = value, req.query.docnumber); */

        var tel = "#frst-tel-number0"
        await page.focus(tel)
        page.keyboard.type(req.query.tel)
        await page.waitFor(500)
        var email = "#email0"
        await page.focus(email)
        page.keyboard.type(req.query.email)
        await page.waitFor(1000)
        
        var clickContinue2 = "#btnSave"
        await page.click(clickContinue2)
        
        var clickContinue3 = "#addSSRContinueBTn"
        await page.waitForSelector(clickContinue3)
        await page.click(clickContinue3)

        var clickContinue4 = "#myModal > div > div > div.modal-footer > button"
        await page.waitFor(2000)

        await page.click(clickContinue3)
        await page.waitFor(300)
        

        

    }
    catch(error){
        //browser.close();
        console.log(error)
    }
    })();
}


app.listen(3000, function() { 
    console.log('server running on port 3000'); 
})