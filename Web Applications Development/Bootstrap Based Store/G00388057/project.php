<?php
//Create a database connection
$dbhost = "localhost";
$dbuser = "root";
$dbpassword = "";
$dbname = "G00388057";

$connection = mysqli_connect($dbhost, $dbuser, $dbpassword, $dbname);

//Test if connection occurred
if (mysqli_connect_errno()) {
  die("DB connection failed: " . mysqli_connect_error() . " (" . mysqli_connect_errno() . ")");
}

if (!$connection) {
  die('Could not connect: ' . mysqli_error());
}

//Save the Query
//This query takes all of the values from the inventory
$sql = "SELECT * FROM inventory";

//Query Database
$result = mysqli_query($connection, $sql);
$row = mysqli_fetch_assoc($result);

//Database connection closed at the end of the HTML section
?>

<!doctype html>
<html lang="en">

<head>
  <!-- Taken from bootstrap website to get the CDN -->
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  <title>G00388057.store</title>
  <!-- Adapted from https://stackoverflow.com/questions/11046904/how-to-set-this-icon-for-the-web-page -->
  <link rel="icon" href="http://cdn.onlinewebfonts.com/svg/img_460503.png">

  <!-- Links to the other files for javascript and css -->
  <link href="style.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="script.js"></script>
</head>

<body onload="randomSetOfImages()">
  <!-- Navbar code -->
  <!-- Code taken from https://getbootstrap.com/docs/4.0/components/navbar/ -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Video Game Shop G00388057</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown">
      <span class="navbar-toggler-icon"></span>
    </button>

    <!-- Drop down button listing the games available on the store taken from the DB -->
    <!-- Code taken from https://getbootstrap.com/docs/4.0/components/navbar/ -->
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown">
            Games
          </a>
          <div class="dropdown-menu">
            <!-- Collects the list of games from DB and creates internal link for user to find the product card on page -->
            <div class='row justify-content-around'>
              <?php
              while ($row = mysqli_fetch_array($result)) {
                echo "<a class='dropdown-item' href='#G0" . $row['PID'] . "'> " . $row['PRODUCT'] . " </a>";
              }
              echo "</div>";
              ?>
            </div>
        </li>
      </ul>
    </div>
    <div>

      <!-- Login button which directs to loginModal -->
      <button type="button" id="loginBtn" class="btn btn-link" data-toggle="modal" data-target="#loginModal">Login to shop</button>
  </nav>

  <!-- Jmbotron logic -->
  <!-- Code taken from https://getbootstrap.com/docs/4.0/components/jumbotron/ -->
  <div id="jumbotron" class="jumbotron">
    <h1 class="display-4">Welcome to G0038057.store</h1>
    <p class="lead" id="jumbotronMessage">Please log in to make a purchase</p> <!-- ID is jumbotronMessage because there is only one instance of a jumbotron message -->
    <hr class="my-4">
    <p class="lead">
      <button type="button" id="basketBtn" class="btn btn-link" data-toggle="modal" data-target="#basketModal" onclick="generateTable()" disabled>View Basket</button>
    </p>
  </div>

  <!-- Carousel Logic -->
  <!-- Code adapted from https://getbootstrap.com/docs/4.0/components/carousel/ -->
  <div class="container">
    <div id="carousel" class="carousel slide" data-ride="carousel" data-interval=3000>
      <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
      </ol>

      <!-- Logic for images -->
      <div class="carousel-inner mw-175" id="carousel">
        <div class="carousel-item active">
          <!-- IDs are selected and used here to locate the images to load randomly -->
          <img id="img1" class="rounded mx-auto d-block img-fluid w-50" src="">
        </div>
        <div class="carousel-item">
          <img id="img2" class="rounded mx-auto d-block img-fluid w-50" src="">
        </div>
        <div class="carousel-item">
          <img id="img3" class="rounded mx-auto d-block img-fluid w-50" src="">
        </div>
      </div>
    </div>
  </div>
  <br>

  </div>


  <!-- PHP code to get the products as cards -->
  <div class="container">
    <?php

    //SQL query from database
    $result = mysqli_query($connection, "SELECT * FROM inventory");

    echo "<div class='row'>";

    //For each row from the query, create a card with game info
    while ($row = mysqli_fetch_assoc($result)) {

      //Assigning the queries to values for legibility
      $product = $row['PRODUCT'];
      $price =  $row['PRICE'];
      $pid = 'G0' . $row['PID']; //This is better than having numeric values for clarity
      $imageSource = $row['IMAGE_SOURCE'];
      $productDescription = $row['PRODUCT_DESCRIPTION'];
      $availableStock = $row['AVAILABLE_STOCK'];
      $elementID = "validationCustom" . $row['PID'];

      //Card code adapted from https://getbootstrap.com/docs/4.0/components/card/
      //Sets the identifier to locate with the dropdown from the nav bar
      echo "<div class='col-sm' id = " . $pid . ">";
      echo "<div class='card' style='width: 18rem;'>";
      //Sets the image for the card
      echo "<img class='card-img-top' src='" . $imageSource . "' alt=" . $product . " Game Banner'>";
      echo "<div class='card-body'>";
      //Sets the name of the game in the card
      echo "<h5 class='card-title'> " . $product . " </h5>";
      //Sets the description of the game as well as the price
      echo "<p id='overflowText' class='card-text'>" . $productDescription . "</p>";
      echo "<p class='card-text text-center'> Price €" . $price . "</p>";
      //Sends the product info as well as the price and the quatity to be added to the basket
      echo "<div class='input-group mb-3'>
         <form onsubmit='addToBasket(\"" . $product . "\", document.getElementById(\"" . $elementID . "\").value, " . $price . ")' >
              <input type='number' class='form-group' id='" . $elementID . "' type='number' min=1 max = " . $availableStock . " placeholder='Quantity' required>
                <button type='submit' class='btn btn-primary btn-lg btn-block' data-toggle='tooltip' data-placement='top' title='" . $availableStock . " available'>Add to basket</button> 
                </form>
            </div>";
      echo "</div>";
      echo "</div>";
      echo "<br>";
      echo "</div>";
      echo "<br>";
    }
    echo "</div>";
    ?>

  </div>


  <!-- Login modal Logic -->
  <!-- Code adapted from https://getbootstrap.com/docs/4.0/components/modal/ -->
  <div class="modal fade" id="loginModal" tabindex="-1" role="dialog"> <!-- id is loginModal to identify as opposed to basketModel -->
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="loginModalLabel">Please log in</h5>
          <button type="button" class="close" data-dismiss="modal">
            <span>&times;</span>
          </button>
        </div>

        <!-- Takes the username from the user -->
        <!-- form control adapted from https://getbootstrap.com/docs/4.0/components/forms/ -->
        <div class="modal-body">
          <form>
            <div class="form-group row">
              <label class="col-sm-2 col-form-label">User name</label>
              <div class="col-sm-10 text-center">
                <input class="form-control" id="inputUserName" placeholder="Enter user name" required="required">
              </div>
            </div>

            <!-- Takes the password from the user -->
            <div class="form-group row">
              <label for="inputPassword" class="col-sm-2 col-form-label" required="required">Password</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword" placeholder="Password"> <!-- ID is inputPassword as it's unique, there is onl one input password throughout -->
              </div>
            </div>
            <!-- Error message is the credentials aren't correct -->
            <p id="errorMessage" hidden="true">Your login credentials are incorrect</p> <!-- ID is errorMessage as there is only one error message throughout -->
        </div>

        <!-- Buttons to either login as user or to continue as guest -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="loginAsGuest()">Continue as guest</button>
          <button type="submit" class="btn btn-primary" onclick="loginCheck()">Login</button> 
          </form>
        </div>
      </div>
    </div>
  </div>

  <!-- Logic for the basket modal, displays the basket contents -->
  <div class="modal fade" id="basketModal" tabindex="-1" role="dialog"> <!-- id is basketModal to identify as opposed to loginModel -->
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="loginModalLabel">Welcome to the Basket</h5>
          <button type="button" class="close" data-dismiss="modal">
            <span>&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <!-- The table generated by the generateTable() function -->
          <table class="table table-hover table-striped">
            <thead id="myTableHead"></thead> <!-- ID is myTableHead as there is only one table head throughout -->
            <tbody id="myTableBody"></tbody> <!-- ID is myTableBody as there is only one table body throughout -->
          </table>
          <!-- Displays the basket total -->
          <h3 id="basketTotal" class="text-center"></h3> <!-- ID is basketTotal as there is only one instance of a basket total in this page -->
          <!-- Message when the basket is empty -->
          <h3 id="tableEmpty" class="text-center">Your basket is empty</h3> <!-- ID is tableEmpty as there is only one palce where this message can appear in this page -->
          <form onsubmit="completePurchase()">
            <!-- Email required to login -->
            <h5 id="checkoutEmail" class="text-center">Please enter email to checkout</h5> <!-- ID is checkoutEmail as there is only one instance of a checkoutEmail in this page -->
            <div class="container">
              <input type="email" class="form-control" id="inputEmail" placeholder="Enter email" required="required">
            </div>
        </div>
        <div class="modal-footer">
          <!-- Buttons to clear the basket or purchase -->
          <button type="submit" class='btn btn-primary'> Purchase </button>
          </form>
          <button onclick="emptyBasket()" class="btn btn-secondary"> Clear Basket </button>
        </div>
      </div>
    </div>
  </div>

  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

  <!-- have to close the connection here otherwise the other php code won't work -->
  <?php mysqli_close($connection); ?>


</body>
<script>
  //Required to have the tooltip display correctly with the units in stock
  //Tried inserting it into the script.js file but it wouldn't work
  //Code adapted from https://getbootstrap.com/docs/4.0/components/tooltips/
  $("[data-toggle=tooltip]").tooltip();
</script>

</html>