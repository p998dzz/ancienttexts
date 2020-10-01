var previousState = "";
var currentList = [];
var backupList = [];
var currentTablet = {};
var currentSort = 0;
var currentOption = 0;
var currentPage = 1;
var pageCount;
const PAGE_SIZE = 20;

function onLoad(){
    loadEntries(0);
}

function loadEntries(option){
    $.ajax(
    { url: "text/all/",
      success: function(result){
        currentList = result.entries;
        backupList = currentList;
        recalculatePageCount();
        reorganize(option);
      },
      error: function(result){
        $("#result").html("There was an error");
      }
    }
  );
}

function recalculatePageCount(){
    pageCount = Math.floor(currentList.length / 20);
    if(currentList.length % PAGE_SIZE != 0){
        pageCount++;
    }
}

function showTextDetails(id){
    $.ajax(
      { url: "text/item/"+id,
        success: function(result){
            previousState = $("#content").html();
            $.ajax(
               { url: "templates/tablet details template.html",
                 success: function(result2){
                   result2 = result2.replace("@paramTitle",result.title);
                   result2 = result2.replace("@paramDescription",result.description);
                   result2 = result2.replace("@paramLocation",result.location);
                   result2 = result2.replace("@paramSource",result.tabletSource);
                   result2 = result2.replaceAll("@paramImageId",result.originalPictureId);
                   result2 = result2.replace("@paramDate",result.dateAdded);
                   result2 = result2.replace("@paramTranslation",result.interpreted === true ? result.translation : "N/A");
                   $("#content").html(result2);
                 },
                 error: function(result2){
                   $("#content").html("There was a server error");
                 }
               }
             );

          },
        error: function(result){
            $("#content").html("there was an error");
        }
      }
    );
}

function restorePreviousState(){
    $("#content").html(previousState);
}

function reorganize(option){
    currentOption = option;
    switch (option){
        case 1:
            if(currentList.length > 0){
                if(currentSort != option){
                    currentList.sort(function(a, b){return a.location.localeCompare(b.location);});
                    currentSort = option;
                } else {
                    currentList.sort(function(a, b){return b.location.localeCompare(a.location);});
                    resetCurrentSort();
                }
            }
            break;
        case 2:
            if(currentList.length > 0){
                if(currentSort != option){
                    currentList.sort(function(a, b){return a.dateAdded.localeCompare(b.dateAdded);});
                    currentSort = option;
                } else {
                    currentList.sort(function(a, b){return b.dateAdded.localeCompare(a.dateAdded);});
                    resetCurrentSort();
                }
            }
            break;
        default:
            if(currentList.length > 0){
                if(currentSort != option){
                    currentList.sort(function(a, b){return a.title.localeCompare(b.title);});
                    currentSort = option;
                } else {
                    currentList.sort(function(a, b){return b.title.localeCompare(a.title);});
                    resetCurrentSort();
                }
            }
            break;
    }
    displayList();
}

function resetCurrentSort(){
    currentSort = 999;
}

function displayList(){
    $("#searchField").on('keypress',function(e) {
        if(e.which == 13) {
            if($("#searchField").val().length > 2){
                searchEntries($("#searchField").val());
            }
        }
    });
    $.ajax(
      { url: "templates/text table template.html",
        success: function(listTable){

            previousState = $("#content").html();
            $.ajax(
               { url: "templates/text row template.html",
                 success: function(rowTemplate){
                   var i = 0;
                   var end = currentList.length;
                   if(pageCount > 1){
                       i = (currentPage-1) * PAGE_SIZE;
                       end = end - i > PAGE_SIZE ? i + PAGE_SIZE : end;
                   }
                   var rowList = "";
                   for(i; i < end; i++){
                       var replacedRow = rowTemplate.replace("",""); //clone string
                       if(i % 2 == 1) {
                           replacedRow = replacedRow.replace("@param1", "odd");
                       } else {
                           replacedRow = replacedRow.replace("@param1", "");
                       }
                       replacedRow = replacedRow.replaceAll("@param6", currentList[i].id);
                       replacedRow = replacedRow.replace("@param2", currentList[i].title);
                       replacedRow = replacedRow.replace("@param3", currentList[i].location);
                       replacedRow = replacedRow.replace("@param4", (currentList[i].interpreted ? "Yes" : "No"));
                       replacedRow = replacedRow.replace("@param5", currentList[i].dateAdded);
                       rowList = rowList + replacedRow;
                   }
                   if(currentList.length == 0){
                       $("#result").html("No records exist for query...");
                   } else {
                       listTable = listTable.replace("@ParamList", rowList);
                       $("#result").html(listTable);
                   }
                   displayPageSelection();
                 },
                 error: function(result2){
                   $("#result").html("There was a server error");
                 }
               }
             );

          },
        error: function(result){
            $("#result").html("there was an error");
        }
      }
    );
}

function displayPageSelection(){
    if(pageCount < 2) {
        $(".pagination").css('display','none');
        return;
    }
    if(pageCount == 2) {
        $("#pageOptionFirst").show();
        $("#pageOptionFirst").html(1);
        $("#pageOptionFirst").val(1);
        $("#pageOptionMiddle").css('display','none');
        $("#pageOptionLast").show();
        $("#pageOptionLast").html(2);
        $("#pageOptionLast").val(2);
        $(".pagination").css('display','flex');
    } else if(pageCount > 2 && currentPage == 1) {
        $("#pageOptionFirst").show();
        $("#pageOptionFirst").html(1);
        $("#pageOptionFirst").val(1);
        $("#pageOptionMiddle").css('display','block');
        $("#pageOptionMiddle").html(currentPage+1);
        $("#pageOptionMiddle").val(currentPage+1);
        $("#pageOptionLast").show();
        $("#pageOptionLast").html(pageCount);
        $("#pageOptionLast").val(pageCount);
        $(".pagination").css('display','flex');
    } else if (pageCount > 2 && pageCount == currentPage) {
        $("#pageOptionFirst").show();
        $("#pageOptionFirst").html(1);
        $("#pageOptionFirst").val(1);
        $("#pageOptionMiddle").css('display','block');
        $("#pageOptionMiddle").html(currentPage-1);
        $("#pageOptionMiddle").val(currentPage-1);
        $("#pageOptionLast").show();
        $("#pageOptionLast").html(pageCount);
        $("#pageOptionLast").val(pageCount);
        $(".pagination").css('display','flex');
    } else {
         $("#pageOptionFirst").show();
         $("#pageOptionFirst").html(1);
         $("#pageOptionFirst").val(1);
         $("#pageOptionMiddle").css('display','block');
         $("#pageOptionMiddle").html(currentPage);
         $("#pageOptionMiddle").val(currentPage);
         $("#pageOptionLast").show();
         $("#pageOptionLast").html(pageCount);
         $("#pageOptionLast").val(pageCount);
         $(".pagination").css('display','flex');
     }
     $("#pageOptionFirst").parent().removeClass("active");
     $("#pageOptionMiddle").parent().removeClass("active");
     $("#pageOptionLast").parent().removeClass("active");
     if($("#pageOptionFirst").val() == currentPage) {
        $("#pageOptionFirst").parent().addClass("active");
     }
     if($("#pageOptionMiddle").val() == currentPage) {
        $("#pageOptionMiddle").parent().addClass("active");
     }
     if($("#pageOptionLast").val() == currentPage) {
        $("#pageOptionLast").parent().addClass("active");
     }
}

function switchPage(el){
    if($(el).attr("id") === "previousButton"){
        if(currentPage > 1){
            currentPage--;
        }
    }else if($(el).attr("id") === "nextButton"){
        if(currentPage < pageCount){
            currentPage++;
        }
    } else {
        var pageNr = $(el).val();
        currentPage = parseInt(pageNr);
    }
    displayList();
}

function searchEntries(searchQuery){
     currentList = backupList;
     var query = searchQuery.toLowerCase();
     var newList = [];
     for(var i = 0; i < currentList.length; i++){
        if (currentList[i].title.toLowerCase().includes(query)){
            newList.push(currentList[i]);
        }
        if (currentList[i].location.toLowerCase().includes(query)){
            newList.push(currentList[i]);
        }
        if (currentList[i].dateAdded.toLowerCase().includes(query)){
            newList.push(currentList[i]);
        }
     }
     backupList = currentList;
     currentList = newList;
     recalculatePageCount();
     currentPage = 1;
     reorganize(currentOption);
}

function clearSearch(){
    currentList = backupList;
    recalculatePageCount();
    reorganize(currentOption);
}

function loadAbout(){
    previousState = $("#content").html();
    $.ajax(
       { url: "templates/about template.html",
         success: function(result){
           $("#content").html(result);
         },
         error: function(result){
           $("#content").html("There was a server error");
         }
       }
     );
}