/* Author: michal harakal

*/


function setColor(color_txt) {
  //           color_txt = global_r.toString(16) + global_g.toString(16) + global_b.toString(16);
 $.ajax({
   url: 'rr/L1/RGB/0',
   type: 'POST',
   data:"color=" + color_txt,   
   success: function(data) {
     alert(data);
   }
 });
}

function makeButtonsTouchable() {
  
  $('.basic-button').bind('touchstart', function(e) {
      e.preventDefault();
      //$(this).addClass('hover_effect');
    });

  $('.basic-button').bind('touchend', function(e) {
      e.preventDefault();
      setColor($(this).getAttribute('rel')) 
      //$(this).addClass('hover_effect');
      // get color of div
    });
}

function makeButtonsClickable() {
  
  $('.basic-button').bind('click', function(e) {
    setColor($(this)[0].getAttribute('rel')) 
    });
 
}

function handlOnDocumentReady()
{
  makeButtonsClickable();
  makeButtonsTouchable();
  
}

$(document).ready(handlOnDocumentReady());





















