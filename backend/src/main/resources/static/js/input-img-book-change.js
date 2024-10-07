const imgTag = document.getElementById('input-book-img-box__input-id');
imgTag.addEventListener('change', function (event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const imagePreview = document.getElementById('input-book-img-box__img-id');
            imagePreview.src = e.target.result;
            imagePreview.style.display = 'black';
        };
        reader.readAsDataURL(file);
    }
});