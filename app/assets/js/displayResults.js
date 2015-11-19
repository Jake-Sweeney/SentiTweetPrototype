$(function() {
  return $.get("/results", function(resultsBody) {
    return $.each(resultsBody, function(index, value) {
      return $("#resultList").append($("<li>").text(value.tweet));
    });
  });
});