const form = document.querySelector('#buy-articles');

form.addEventListener('submit', function (e) {
    // prevent the form from submitting
    e.preventDefault();

   	if (confirm("¿Estás seguro/a de querer realizar tu compra?")) {
    	form.submit();
    } else {
    	window.location='show-cart.jsp';
    }
});