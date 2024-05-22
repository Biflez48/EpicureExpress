$(document).ready(function(){
    function updateTotalPrice() {
        let totalPrice = 0;
        $('.products-list li').each(function(){
            const price = parseFloat($(this).find('p:eq(0)').text());
            const count = parseInt($(this).find('input').val());
            totalPrice += price * count;
        });
        $('#total-price').text(totalPrice.toFixed(2));
    }

    $('.edit-cnt-bucket-btn').click(function(){
        var button = $(this);
        var productId = button.data('id');
        var isIncrement = button.text() === '+';
        var input = button.siblings('input');
        var currentCount = parseInt(input.val());
        var newCount = isIncrement ? currentCount + 1 : currentCount - 1;

        if (newCount < 1) {
            newCount = 1;
        }

        $.ajax({
            type: 'POST',
            url: '/edit-bucket',
            data: JSON.stringify({ productId: productId, increment: isIncrement }),
            contentType: 'application/json',
            success: function(response) {
                if (response.success) {
                    input.val(newCount);
                    button.siblings('.edit-cnt-bucket-btn').prop('disabled', false);
                    if (newCount <= 1) {
                        button.prop('disabled', true);
                    }
                    updateTotalPrice();
                } else {
                    alert('Ошибка изменения количества товара');
                }
            },
            error: function() {
                alert('Ошибка запроса');
            }
        });
    });

    $('.delete-from-bucket-btn').click(function(){
        var button = $(this);
        var productId = button.data('id');
        $.ajax({
            type: 'POST',
            url: '/delete-from-bucket',
            data: JSON.stringify({ productId: productId }),
            contentType: 'application/json',
            success: function(response) {
                if (response.success) {
                    button.closest('li').remove();
                    updateTotalPrice();
                } else {
                    alert('Ошибка удаления товара из корзины');
                }
            },
            error: function() {
                alert('Ошибка запроса');
            }
        });
    });

    updateTotalPrice();

    const checkoutBtn = document.getElementById('checkout-btn');
    const orderOverlay = document.getElementById('order-overlay');
    const orderPopup = document.getElementById('order-popup');
    const cancelOrderBtn = document.getElementById('cancel-order-btn');

    if (checkoutBtn) {
        checkoutBtn.addEventListener('click', () => {
            console.log('Checkout button clicked');
            orderOverlay.style.display = 'block';
            orderPopup.style.display = 'block';
        });
    }

    if (cancelOrderBtn) {
        cancelOrderBtn.addEventListener('click', closeOrderForm);
    }

    function closeOrderForm() {
        orderOverlay.style.display = 'none';
        orderPopup.style.display = 'none';
    }
});


