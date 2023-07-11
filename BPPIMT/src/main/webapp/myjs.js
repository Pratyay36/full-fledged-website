let navbar = document.querySelector('.navbar');

 window.onscroll = () =>{
    navbar.classList.remove('active');
    searchForm.classList.remove('active');
    cartItem.classList.remove('active');
 }

 function openFile() {
    var fileName = document.getElementById("blogs").value;
    if (fileName) {
      window.location.href = fileName;
    }
  }