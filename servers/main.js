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