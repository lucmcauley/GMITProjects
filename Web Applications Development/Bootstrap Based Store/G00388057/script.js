//Calls the functions which will generate the table
function generateTable() {
  generateTableHead();
  generateTableContents();
}

//Generates the table head with the titles
function generateTableHead() {

  //This needs to be above the row variable to ensure the table head is not empty
  var table = document.getElementById("myTableHead");
  table.innerHTML = ""; //clears the table to avoid rows appearing everytime the basketModal is loaded multiple times

  let titles = ["Title", "Price Per Unit", "Quantity", "Total"]
  var row = table.insertRow(0);

  //Titles the table from the titles array
  for (let i = 0; i < titles.length; i++) {
    var cell = row.insertCell(i);
    cell.innerHTML = titles[i];
  }
}

//Generates the contents of the table using information in the persistent local storage.
function generateTableContents() {
  let basket = localStorage.getItem("basketContent");
  var table = document.getElementById("myTableBody");
  let basketTotal = 0;

  table.innerHTML = ""; //clears the table to avoid rows appearing everytime the basketModal is loaded multiple times

  //Checks to see if the basket is empty
  if (basket == null) {
    document.getElementById("tableEmpty").style.visibility = "visible";
    document.getElementById("checkoutEmail").style.visibility = "hidden";
    document.getElementById("inputEmail").style.visibility = "hidden";
    document.getElementById("basketTotal").style.visibility = "hidden";

  } else {
    document.getElementById("tableEmpty").style.visibility = "hidden";
    document.getElementById("checkoutEmail").style.visibility = "visible";
    document.getElementById("inputEmail").style.visibility = "visible";
    document.getElementById("basketTotal").style.visibility = "visible";

    let basketItems = basket.split(',');

    //uses both session storage to get and calculate the contents of the basket. 
    //could probably be coded more clearly
    for (let i = 0; i < basketItems.length; i++) {

      let title = basketItems[i];
      let temp = localStorage.getItem(title);
      let tempJSON = JSON.parse(temp);
      let updatedQuantity = parseInt(tempJSON.amount) * parseInt(tempJSON.quantity);

      var row = table.insertRow(i);
      var cell1 = row.insertCell(0);
      var cell2 = row.insertCell(1);
      var cell3 = row.insertCell(2);
      var cell4 = row.insertCell(3);
      cell1.innerHTML = title;
      cell2.innerHTML = tempJSON.amount;
      cell3.innerHTML = tempJSON.quantity;
      cell4.innerHTML = updatedQuantity;
      basketTotal += updatedQuantity
    }
    document.getElementById("basketTotal").innerHTML = "Basket total: " + basketTotal;
  }
}

//Adds elements to the basket through the use of local storage
//There should also be validation for the total amount, can just add 1 continuously to go above the amount in stock
function addToBasket(productName, quantity, price) {
  this.productName = productName;
  this.quantity = quantity;
  let temp = localStorage.getItem(productName);

  //first updates the localStorage basket
  updateBasketContents(productName);

  //Checks to see if the basket is currently empty
  if (temp == null) { //If basket is empty, create a new cart object to place into basket
    let tempObject = new cartContent(productName, price, quantity);
    localStorage.setItem(productName, JSON.stringify(tempObject));

  } else { //If basket is not empty, update the basket wit new cart object
    let oldQuantity = JSON.parse(temp).quantity;
    let updatedQuantity = parseInt(quantity) + parseInt(oldQuantity);
    let tempObject = new cartContent(productName, price, updatedQuantity); //Creates new Cart Object
    localStorage.setItem(productName, JSON.stringify(tempObject));
  }
}

//Adds the title of the game to an array keeping track of the titles in the basket
function updateBasketContents(productName) {
  let basket = localStorage.getItem("basketContent");

  //Checks to see if there is a basket item locally stored
  if (basket == null) { //If no basket array present, create new basket array
    basket = productName;
    localStorage.setItem("basketContent", basket);
  } else { //If there is a basket array, update the array with the new item. 
    let basketItems = basket.split(',');
    if (basketItems.indexOf(productName) < 0) {
      localStorage.setItem("basketContent", basket.concat(",", productName));
    }
  }
}

//Empties the basket and displays the correct messages
function emptyBasket() {
  localStorage.clear()
  var table = document.getElementById("myTableBody");
  table.innerHTML = "";
  document.getElementById("tableEmpty").style.visibility = "visible";
  document.getElementById("checkoutEmail").style.visibility = "hidden";
  document.getElementById("inputEmail").style.visibility = "hidden";
  document.getElementById("basketTotal").style.visibility = "hidden";
}

//Cart Content object
function cartContent(product, amount, quantity) {
  this.product = product;
  this.amount = amount;
  this.quantity = quantity;
}

//Selects the random set of images that appear on the carousel
function randomSetOfImages() {
  let randomValue = Math.floor((Math.random() * 3) + 1);

  //Uses both URL and locally stored images
  let imgSrc1 = ["https://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_download_software_1/H2x1_NSwitchDS_Carto_image1600w.jpg", "https://cdn03.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_TheLegendOfZeldaLinksAwakening_image1600w.jpg", "https://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/wiiu_14/SI_WiiU_SuperMario3DWorld_image1600w.jpg"];
  let imgSrc2 = ["Images/Halo.jpg", "Images/DoomEternal.jpg", "Images/PrisonArchitect.jpg"];
  let imgSrc3 = ["https://wallpapercave.com/wp/wp4662802.jpg", "https://store-images.s-microsoft.com/image/apps.57392.66675947096956870.b4e29591-bb26-46a2-9186-b8bf00da4ecc.e89ee15e-779f-4ad0-aa68-a249d2a9aae7?mode=scale&q=90&h=1080&w=1920&background=%23FFFFFF", "https://twistedvoxel.com/wp-content/uploads/2020/09/super-mario-35th.jpg"];

  //Assigns the random set of images according to the random value obtained above
  if (randomValue == 1) {
    document.getElementById("img1").src = imgSrc1[0];
    document.getElementById("img2").src = imgSrc1[1];
    document.getElementById("img3").src = imgSrc1[2];
  } else if (randomValue == 2) {
    document.getElementById("img1").src = imgSrc2[0];
    document.getElementById("img2").src = imgSrc2[1];
    document.getElementById("img3").src = imgSrc2[2];
  } else {
    document.getElementById("img1").src = imgSrc3[0];
    document.getElementById("img2").src = imgSrc3[1];
    document.getElementById("img3").src = imgSrc3[2];
  }
}

//Checks for the input from the login modal
function loginCheck() {
  let email = document.getElementById("inputUserName").value;
  let pass = document.getElementById("inputPassword").value;
  let name = "User"

  //Hardcoded values for username and password
  if (email == "user" && pass == "pass") {
    loggedIn(name);
    document.getElementById("jumbotron").style.color = "green";
    //adapted from https://stackoverflow.com/questions/33477930/close-bootstrap-modal-on-form-submit to close modal
    $('#loginModal').modal('hide');
  } else {//Displays the invalid credential message 
    document.getElementById("errorMessage").removeAttribute("hidden");
  }
}

//Function for when guest logs in
function loginAsGuest() {
  document.getElementById("jumbotron").style.color = "blue"; //Changes the colour of the text in the jumbotron once logged in
  loggedIn("Guest");
}

//Changes made to jumbotron and login button when successfully logged in
function loggedIn(name) {
  this.name = name;

  document.getElementById("jumbotronMessage").innerHTML = "Welcome " + name;
  document.getElementById("loginBtn").innerHTML = "logged in";
  document.getElementById("loginBtn").setAttribute("disabled", "true");
  document.getElementById("basketBtn").removeAttribute("disabled");
  document.getElementById("errorMessage").setAttribute("hidden", "true");

  alert("Welcome " + name + "!");
}

//Alert that displays when the purchase is complete
function completePurchase() {
  alert("Thank you for your purchase!");
  $('#basketModal').modal('hide');
  //resets the checkout email value as it avoids being able to checkout with an empty basket after first purchase
  document.getElementById('inputEmail').value = "";
}

