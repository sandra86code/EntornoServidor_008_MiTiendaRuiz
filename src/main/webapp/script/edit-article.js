const form = document.querySelector('#edit-article');

form.addEventListener('submit', function (e) {
    // prevent the form from submitting
    e.preventDefault();

   	if (confirm("¿Estás seguro/a de querer modificar el artículo?")) {
    	form.submit();
    } else {
    	window.location='show-articles';
    }
});