const imageInput = document.getElementById('image-input');
const selectImageBtn = document.getElementById('select-image-btn');
const imageOutput = document.getElementById('image-output');
const addCategoryBtn = document.getElementById('add-category-btn');
const categorySelect = document.getElementById('category-select');
const selectedCategoriesDiv = document.getElementById('selected-categories');

selectImageBtn.addEventListener('click', () => {
    imageInput.click();
});

imageInput.addEventListener('change', (e) => {
    if (e.target.files && e.target.files[0]) {
        const file = e.target.files[0];
        const reader = new FileReader();

        reader.onload = (event) => {
            imageOutput.style.backgroundImage = `url(${event.target.result})`;
        };

        reader.readAsDataURL(file);
    }
});

addCategoryBtn.addEventListener('click', () => {
    const selectedCategory = categorySelect.options[categorySelect.selectedIndex];
    const categoryId = selectedCategory.value;
    const categoryName = selectedCategory.text;

    if (!isCategoryAlreadySelected(categoryId)) {
        const categoryDiv = document.createElement('div');
        categoryDiv.classList.add('category-item');
        categoryDiv.setAttribute('data-category-id', categoryId);
        categoryDiv.textContent = categoryName;

        const removeBtn = document.createElement('button');
        removeBtn.textContent = 'X';
        removeBtn.addEventListener('click', () => {
            categoryDiv.remove();
            updateCategoriesInput();
        });

        categoryDiv.appendChild(removeBtn);
        selectedCategoriesDiv.appendChild(categoryDiv);

        updateCategoriesInput();
    }
});

const isCategoryAlreadySelected = (categoryId) => {
    return Array.from(selectedCategoriesDiv.children).some(item => item.getAttribute('data-category-id') === categoryId);
};

const updateCategoriesInput = () => {
    const selectedCategories = [];
    document.querySelectorAll('.category-item').forEach(item => {
        selectedCategories.push(item.getAttribute('data-category-id'));
    });

    let hiddenInput = document.querySelector('input[name="categories"]');
    if (!hiddenInput) {
        hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'categories';
        document.querySelector('form').appendChild(hiddenInput);
    }
    hiddenInput.value = selectedCategories.join(',');
};

document.querySelector('form').addEventListener('submit', (e) => {
    updateCategoriesInput();
});
