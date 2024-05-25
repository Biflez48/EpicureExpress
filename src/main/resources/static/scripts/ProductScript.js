$(document).ready(function(){
    $('.add-to-bucket-btn').click(function(){
        var button = $(this);
        var productId = button.data('id');
        $.ajax({
            type: 'POST',
            url: '/add-to-bucket',
            data: JSON.stringify({ productId: productId }),
            contentType: 'application/json',
            success: function(response) {
                if (response.success) {
                    button.text('Уже в корзине').attr('disabled', true);
                } else {
                    alert('Ошибка добавления товара в корзину');
                }
            },
            error: function() {
                alert('Вы не авторизированны!');
            }
        });
    });
    $('.delete-prod-btn').click(function(){
        var button = $(this);
        var productId = button.data('id');
        $.ajax({
            type: 'POST',
            url: '/delete-prod',
            data: JSON.stringify({ productId: productId }),
            contentType: 'application/json',
            success: function(response) {
                if (response.success) {
                    window.location.reload();
                } else {
                    alert('Ошибка удаления товара');
                }
            },
            error: function() {
                alert('Вы не авторизированны!');
            }
        });
    });
});
