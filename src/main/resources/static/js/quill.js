var toolbarOptions = [
              ['bold', 'italic'],        // toggled buttons
              ['underline', 'strike'],        // toggled buttons
              ['blockquote', 'code-block'],

              [{ 'header': 1 }, { 'header': 2 }],               // custom button values
              [{ 'list': 'ordered'}, { 'list': 'bullet' }],
              //[{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
              [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
              [{ 'direction': 'rtl' }, { 'align': [] }],                         // text direction

              [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme

              ['video', 'image'],

              //['clean']                                         // remove formatting button
            ];

            var quill = new Quill('#editor', {
              modules: {
                toolbar: toolbarOptions
              },
              theme: 'snow'
            });

             var italic = Quill.import('formats/italic');
              italic.tagName = 'i';
              Quill.register(italic, true);

