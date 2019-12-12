window.onload = function () {
    $('.loader').fadeOut();
};

$('.menu').find('li').each(function (i) {
    $(this).click(function () {
        $('.menu').find('li').removeClass('on').eq(i).addClass('on');
        $('.cont').height(0).eq(i).height('auto');
        $(document).scrollTop(0);
    });
}).first().click();

$('.menu2').each(function (i) {
    $(this).find('span').each(function (j) {
        $(this).click(function () {
            $('.menu2').eq(i).find('span').removeClass('on').eq(j).addClass('on');
            $('.menu2').eq(i).nextAll('.cont2').height(0).eq(j).height('auto');
            $(document).scrollTop(0);
        });
    }).first().click();
});

$('.echarts-btn').each(function (i) {
    $(this).click(function fnClick() {
        if ($(this).hasClass('on')) {
            $('.echarts-table').eq(i).hide();
            $(this).removeClass('on');
        } else {
            $('.echarts-table').eq(i).show();
            $(this).addClass('on');
        }
    });
});