const formDelete = document.querySelector('#delete-article');

formDelete.addEventListener('submit', function (e) {
    // prevent the form from submitting
    e.preventDefault();

   	if (confirm("¿Estás seguro/a de querer borrar el artículo?")) {
    	formDelete.submit();
    } else {
    	window.location='show-articles';
    }
});