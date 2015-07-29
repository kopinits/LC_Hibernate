function strTrim(str) {
    return str.replace(/^\s+|\s+$/gm, '');
}

function isEmail(email) {
    var str = email;
    var filtro = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    if (filtro.test(str)) {
        return true;
    } else {
        return false;
    }
}

function somenteNumero(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    var letras = /[a-z]/;

    if (e.srcElement) {
        if (letras.test(e.srcElement.value)) {
            e.srcElement.value = "";
        }
    } else {
        if (letras.test(e.target.value)) {
            e.target.value = "";
        }
    }
    if ((tecla > 47 && tecla < 58)) {
        return true;
    } else {
        if (tecla != 8 && tecla != 13 && tecla != 0)
            return false;
        else
            return true;
    }
}

// ======= Valida telefone com 8 e 9 numeros com hifen ========
function mascara(o, f) {
    v_obj = o;
    v_fun = f;
    setTimeout(execmascara, 1);
}
function execmascara() {
    v_obj.value = v_fun(v_obj.value);
}
function mtel(v) {
    v = v.replace(/\D/g, "");
    v = v.replace(/(\d)(\d{4})$/, "$1-$2");
    return v;
}
function formataTelefone(id) {
    var $telefone = document.getElementById(id);
    if ($telefone != null) {
        $telefone.onkeyup = function() {
            mascara(this, mtel);
        }
        $telefone.onblur = function() {
            var v = this.value;
            if (v.indexOf('(11)') !== -1 && v.length === 14) {
                this.value = v.replace(/(\d{4})-(\d{4})/g, '8$2-$3');
            }
            if (v.length > 0 && v.length < 9) {
                alert('Telefone inválido.');
                document.getElementById(id).focus();
                return false;
            }

            var x = v.substring(v.indexOf('-'));

            if (x.length > 0 && x.length < 5) {
                alert('Telefone inválido.');
                document.getElementById(id).focus();
                return false;
            }

        }
    }
}

function mascaraMoeda(objTextBox, SeparadorMilesimo, SeparadorDecimal, e, nrCaracteres) {
    /** * codigo para corrigir erro ao entrar nos campos com essa mascara ** */
    // firefox
    if (objTextBox.selectionStart == '0') {
        objTextBox.value = "";
    }
    // ie
    if (document.selection) {
        range = document.selection.createRange();
        if (range.compareEndPoints("StartToEnd", range) < 0) {
            objTextBox.value = "";
            range.moveStart('character', objTextBox.value.length);
            range.collapse();
            range.select();
        }
    }
    /** * fim codigo para corrigir erro ao entrar nos campos com essa marcara ** */
    var sep = 0;
    var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode = (window.Event) ? e.which : e.keyCode;
    if (whichCode == 13 || whichCode == 8 || whichCode == 0)
        return true;
    key = String.fromCharCode(whichCode); // Valor para o codigo da Chave
    if (strCheck.indexOf(key) == -1)
        return false; // Chave invalida
    len = objTextBox.value.length;
    if (nrCaracteres != null) {
        if (len > (nrCaracteres)) {
            return false;
        }
    }
    for (i = 0; i < len; i++)
        if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != SeparadorDecimal))
            break;
    aux = '';
    for (; i < len; i++)
        if (strCheck.indexOf(objTextBox.value.charAt(i)) != -1)
            aux += objTextBox.value.charAt(i);
    aux += key;
    len = aux.length;
    if (len == 0)
        objTextBox.value = '';
    if (len == 1)
        objTextBox.value = '0' + SeparadorDecimal + '0' + aux;
    if (len == 2)
        objTextBox.value = '0' + SeparadorDecimal + aux;
    if (len > 2) {
        aux2 = '';
        for (j = 0, i = len - 3; i >= 0; i--) {
            if (j == 3) {
                aux2 += SeparadorMilesimo;
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        objTextBox.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--)
            objTextBox.value += aux2.charAt(i);
        objTextBox.value += SeparadorDecimal + aux.substr(len - 2, len);
    }
    return false;
}