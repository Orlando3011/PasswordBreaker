function downloadDiv(filename, elementId, mimeType) {
            var elementHtml = document.getElementById(elementId).innerHTML;
            var link = document.createElement('a');
            mimeType = mimeType || 'text/plain';

            link.setAttribute('download', filename);
            link.setAttribute('href', 'data:' + mimeType  +  ';charset=utf-8,' + encodeURIComponent(elementHtml));
            link.click();
        }