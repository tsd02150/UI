// basic1.js

// document.addEventListener('DOMContentLoaded',function(){
//   let liTag = document.createElement('li'); //Document Object Model
//   liTag.innerText='Cherry';
//   console.log(liTag);
//   document.querySelector('#list').append(liTag);
// })

// $(document).ready(function(){
//   // let elem = jQuery('<li/>');
//   let elem = $('<li/>');
//   elem.text('Cherry');
//   console.log(elem);
//   $('#list').append(elem);
// })

$(document).ready(function () {
  $('#list').append($('<li/>').text('Cherry'),
    $('<li/>').text('Mango'));
})