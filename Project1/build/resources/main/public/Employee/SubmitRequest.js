function loadDoc() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {

        var response = JSON.parse(this.responseText);
        document.getElementById("user").value =
        response.username;
    }
  };
  xhttp.open("POST", "/UserData", true);
  xhttp.send();
}

loadDoc();
