const puppeteer = require('puppeteer');

(async () => {
  const browser = await puppeteer.launch({headless: false});
  const page = await browser.newPage();
  await page.setViewport({ width: 1600, height: 800 })
  await page.goto('https://flyarystan.com/');

  var inputData = "#depDate"
  var findBtn   = "#flightTab > div > div.col-md-1.padding0.flightsearch > button";
  page.waitForSelector(inputFrom);

//   await page.$eval(  // from input 
//     inputFrom, inputFrom => inputFrom.value = "Караганда"
//   );
  
  var inputFrom = "#depPortInput";
  await page.click(inputFrom)
  var fromCity = "#depPortsCust > a:nth-child(5)"
  await page.click(fromCity)

  var inputTo = "#arrPortInput";
  await page.click(inputTo)
  var toCity = "#arrPortsCust > a"
  await page.click(toCity)
  
  
//   await page.$eval( // to input
//     inputTo, inputTo => inputTo.value = "Алматы"
//   );
//   await page.keyboard.press('Enter');
//   page.waitForSelector(inputData);

  await page.click(inputData);
  let oneWay = "#oneway";
  await page.click(oneWay);
  let tempDate = "#datepickerflight > div.datepicker--content > div > div.datepicker--cells.datepicker--cells-days > div:nth-child(23)";
  await page.click(tempDate);
  
  await page.$eval( // date
    inputData, inputData => inputData.value = "23 Июл"
  );
  //await page.click("#flightTab > div > div.col-md-1.padding0.flightsearch > button");
  await page.evaluate((findBtn) => document.querySelector(findBtn).click(), findBtn);
  // @@@@@@@@@@@@@@@@@@@@@@


  const titleSelector = 'div > div.mobile-price.col-xs-4.col-md-12.col-sm-12.col-lg-12 > span';         
  await page.waitForSelector(titleSelector);         
  const pageTitle = await page.$eval(
    titleSelector, titleSelector => titleSelector.innerHTML
  );
  console.log(pageTitle)
  
//   var price = "div > div.mobile-price.col-xs-4.col-md-12.col-sm-12.col-lg-12 > span"
//   await page.waitForSelector(price)
  
//   const text = page.evaluate(() => document.querySelector('div > div.mobile-price.col-xs-4.col-md-12.col-sm-12.col-lg-12 > span').innerText);
//   console.log(text) 
//   console.log(text.innerText)

  await page.screenshot({path: 'example.png'});

  //await browser.close();
})();