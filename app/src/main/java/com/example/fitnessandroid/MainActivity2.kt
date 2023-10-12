package com.example.fitnessandroid

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class MainActivity2 : AppCompatActivity() {

    private var PASSWORD_PATTERN:Pattern = Pattern.compile(
        "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8}" +               //at least 8 characters
                "$"
    )

    private lateinit var loginNameInp:TextInputEditText
    private lateinit var loginPasswordInp:TextInputEditText

    private lateinit var inscriptionFirstAndLastnameInp:TextInputEditText
    private lateinit var inscriptionPseudonymeInp:TextInputEditText
    private lateinit var inscriptionPasswordInp:TextInputEditText
    private lateinit var inscriptionConfirmPasswordInp:TextInputEditText
    private lateinit var passwordForgottenEmailInp:TextInputEditText
    private lateinit var alertConnectionEmailInp:TextInputEditText

    private lateinit var code1Inp:EditText
    private lateinit var code2Inp:EditText
    private lateinit var code3Inp:EditText
    private lateinit var code4Inp:EditText
    private lateinit var code5Inp:EditText
    private lateinit var code6Inp:EditText

    private lateinit var InscriptionGenderM:RadioButton
    private lateinit var InscriptionGenderF:RadioButton
    private lateinit var InscriptionGenderN:RadioButton

    private lateinit var passwordForgotten:TextView

    private lateinit var inscriptionBtn:Button
    private lateinit var inscriptionBtnConnection:Button
    private lateinit var connectionBtn:MaterialButton

    private lateinit var passwordForgottenBtnSend:Button
    private lateinit var alertConnectionBtnSend:Button
    //private lateinit var alertConnectionValidationBtnSend:Button

    private lateinit var inscriptionFirstAndLastnameInpText:String
    private lateinit var inscriptionPseudonymeInpText:String
    private lateinit var inscriptionPasswordInpText:String
    private lateinit var inscriptionConfirmPasswordInpText:String
    private lateinit var passwordForgottenEmailInpText:String
    private lateinit var alertConnectionEmailInpText:String

    private lateinit var loginPasswordInpText:String
    private lateinit var loginNameInpText:String

    private var ii:Int = 0 // ii is the number of times the "connect" button has been clicked

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        loginNameInp = this.findViewById<TextInputEditText>(R.id.loginNameInp)
        loginPasswordInp = this.findViewById<TextInputEditText>(R.id.passwordInp)

        connectionBtn = this.findViewById<MaterialButton>(R.id.connectionBtn)
        inscriptionBtn = this.findViewById<Button>(R.id.inscriptionBtn)

        // Boutton animation, enabled when entering login information

        var loginWatcherText = object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {

                loginNameInpText = loginNameInp.text.toString().trim()

                if ( loginNameInpText.isEmpty() ){

                    loginNameInp.setError("Ce champ ne peut pas être vide")

                    connectionBtn.strokeWidth = 4
                    connectionBtn.setBackgroundColor(android.graphics.Color.TRANSPARENT)
                    connectionBtn.isClickable = false

                }else if (ii>= 1 && loginNameInpText.isNotEmpty() && loginPasswordInpText.isNotEmpty() ){

                    connectionBtn.setBackgroundColor(connectionBtn.context.resources.getColor(R.color.appGreen))
                    connectionBtn.strokeWidth = 0
                    connectionBtn.isClickable = true

                }

            }

        }

        var passwordWatcherText = object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                loginNameInpText = loginNameInp.text.toString().trim()

                if (loginNameInpText.isEmpty()){

                    loginNameInp.setError("Ce champ ne peut pas être vide")

                } else {

                    loginNameInp.setError(null)

                }

            }

            override fun afterTextChanged(s: Editable?) {

                if (loginNameInpText.isNotEmpty()){

                    connectionBtn.setBackgroundColor(connectionBtn.context.resources.getColor(R.color.appGreen))
                    connectionBtn.strokeWidth = 0
                    connectionBtn.isClickable = true

                }

            }

        }

        loginNameInp.addTextChangedListener(loginWatcherText)
        loginPasswordInp.addTextChangedListener(passwordWatcherText)

        // Opened dashboard activity

        connectionBtn.setOnClickListener {

            ii++

            if(ii == 3){

                diplayPopupDialogAlertConnection()

            }else{

                /* We could also use variable ii to detect suspect connections    */

                // Inputs checking and validation

                loginPasswordInpText = loginPasswordInp.text.toString().trim()
                loginNameInpText = loginNameInp.text.toString().trim()

                if (loginPasswordInpText.isEmpty() && loginNameInpText.isEmpty()){

                    loginPasswordInp.setError("Ce champ ne peut pas être vide")
                    loginNameInp.setError("Ce champ ne peut pas être vide")

                } else {

                    loginPasswordInp.setError(null)
                    loginNameInp.setError(null)

                    var loginPasswordLayout = this@MainActivity2.findViewById<TextInputLayout>(R.id.passwordInpLayout)
                    loginPasswordLayout.isPasswordVisibilityToggleEnabled = true

                    if (loginPasswordInpText.equals("M0tdep@ss") && loginNameInpText.equals("nomprenom@gmail.com")){

                        opendashboard()

                    }else{

                        Toast.makeText(this@MainActivity2, "Nom d'utilisateur ou Mot de passe incorrect "+ ii, Toast.LENGTH_LONG).show()

                        connectionBtn.strokeWidth = 4
                        connectionBtn.setBackgroundColor(android.graphics.Color.TRANSPARENT)
                        connectionBtn.isClickable = false

                        loginPasswordInp.setError("")
                        loginNameInp.setError("")

                    }

                }

            }


        }

        // Opened Password forgotten pop up dialog

        passwordForgotten = this.findViewById<TextView>(R.id.passwordForgotten)
        passwordForgotten.setOnClickListener {
            diplayPopupDialogPasswordForgotten()
        }

        // Opened inscription pop up dialog

        inscriptionBtn.setOnClickListener {
            diplayPopupDialogSinscrire()
        }

    }

    private fun opendashboard() {
        val Intent = Intent (this, DashboardActivity::class.java)
        startActivity(Intent)
    }

    private fun diplayPopupDialogPasswordForgotten (){

        var popupDialog = Dialog(this)
        //popupDialog.setCancelable(false)

        popupDialog.setContentView(R.layout.password_forgotten_screen)
        popupDialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

        // Boutton animation, enabled when entering Email for password recuperation

        passwordForgottenBtnSend = popupDialog.findViewById<Button>(R.id.passwordForgottenBtnSend)
        passwordForgottenEmailInp = popupDialog.findViewById<TextInputEditText>(R.id.passwordForgottenEmailInp)

        var recuperationWatcherText = object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                passwordForgottenEmailInpText = passwordForgottenEmailInp.text.toString().trim()

                if(passwordForgottenEmailInpText.isNotEmpty()){

                    passwordForgottenBtnSend.isEnabled = true

                }else{
                    passwordForgottenBtnSend.isEnabled = false
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }

        }

        passwordForgottenEmailInp.addTextChangedListener(recuperationWatcherText)

        passwordForgottenBtnSend.setOnClickListener {

            passwordForgottenEmailInpText = passwordForgottenEmailInp.text.toString().trim()

            if(passwordForgottenEmailInpText.isNotEmpty()){

                passwordForgottenEmailInp.setError(null)

                if (!Patterns.EMAIL_ADDRESS.matcher(passwordForgottenEmailInpText).matches()){

                    passwordForgottenEmailInp.setError("Entrez une adresse Email valide")
                    passwordForgottenBtnSend.isEnabled = false

                } else {

                    Toast.makeText(this@MainActivity2, "Le lien de récupération a bien été envoyé à votre adresse mail ", Toast.LENGTH_LONG).show()
                    popupDialog.dismiss()

                }

            }else{
                passwordForgottenEmailInp.setError("Ce champ ne peut pas être vide")
                passwordForgottenBtnSend.isEnabled = false
            }

        }

        popupDialog.show()

    }

    private fun diplayPopupDialogAlertConnection (){

        ii = 0

        var popupDialog = Dialog(this)
        //popupDialog.setCancelable(false)

        popupDialog.setContentView(R.layout.alert_connection_screen)
        popupDialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

        // Boutton animation, enabled when entering Email

        alertConnectionBtnSend = popupDialog.findViewById<Button>(R.id.alertConnectionBtnSend)
        alertConnectionEmailInp = popupDialog.findViewById<TextInputEditText>(R.id.alertConnectionEmailInp)

        var recuperationWatcherText = object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                alertConnectionEmailInpText = alertConnectionEmailInp.text.toString().trim()

                if(alertConnectionEmailInpText.isNotEmpty()){

                    alertConnectionBtnSend.isEnabled = true

                }else{
                    alertConnectionBtnSend.isEnabled = false
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }

        }

        alertConnectionEmailInp.addTextChangedListener(recuperationWatcherText)

        alertConnectionBtnSend.setOnClickListener {

            alertConnectionEmailInpText = alertConnectionEmailInp.text.toString().trim()

            if(alertConnectionEmailInpText.isNotEmpty()){

                alertConnectionEmailInp.setError(null)

                if (!Patterns.EMAIL_ADDRESS.matcher(alertConnectionEmailInpText).matches()){

                    alertConnectionEmailInp.setError("Entrez une adresse Email valide")
                    alertConnectionBtnSend.isEnabled = false

                } else {

                    Toast.makeText(this@MainActivity2, "Le lien de récupération a bien été envoyé à votre adresse mail ", Toast.LENGTH_LONG).show()
                    popupDialog.dismiss()
                    diplayPopupDialogAlertConnectionValidation()
                }

            }else{
                alertConnectionEmailInp.setError("Ce champ ne peut pas être vide")
                alertConnectionBtnSend.isEnabled = false
            }

        }

        popupDialog.show()

    }

    private fun diplayPopupDialogAlertConnectionValidation (){

        var popupDialog = Dialog(this)
        //popupDialog.setCancelable(false)

        popupDialog.setContentView(R.layout.alert_connection_validation_screen)
        popupDialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

        //alertConnectionValidationBtnSend = popupDialog.findViewById<Button>(R.id.alertConnectionValidationBtnSend)
        //alertConnectionEmailInp = popupDialog.findViewById<TextInputEditText>(R.id.alertConnection)

        var recuperationWatcherText = object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                /* var  = code1Inp.text.toString()

                if(){


                }else{

                }*/

            }

            override fun afterTextChanged(s: Editable?) {
            }

        }

        /*code1Inp.addTextChangedListener(recuperationWatcherText)
        code2Inp.addTextChangedListener(recuperationWatcherText)
        code3Inp.addTextChangedListener(recuperationWatcherText)
        code4Inp.addTextChangedListener(recuperationWatcherText)
        code5Inp.addTextChangedListener(recuperationWatcherText)
        code6Inp.addTextChangedListener(recuperationWatcherText)*/

        //popupDialog.dismiss()


        popupDialog.show()

    }

    private fun diplayPopupDialogSinscrire (){

        var popupDialog = Dialog(this)
        //popupDialog.setCancelable(false)

        popupDialog.setContentView(R.layout.inscription_screen)
        popupDialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

        inscriptionBtnConnection = popupDialog.findViewById<Button>(R.id.inscriptionBtnConnection)

        inscriptionFirstAndLastnameInp = popupDialog.findViewById<TextInputEditText>(R.id.alertConnectionEmailInp)
        inscriptionPseudonymeInp = popupDialog.findViewById<TextInputEditText>(R.id.inscriptionPseudonymeInp)
        inscriptionPasswordInp = popupDialog.findViewById<TextInputEditText>(R.id.inscriptionPasswordInp)
        inscriptionConfirmPasswordInp = popupDialog.findViewById<TextInputEditText>(R.id.inscriptionConfirmPasswordInp)

        // Boutton animation, enabled when entering inscription information

        var inscriptionWatcherText = object : TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                inscriptionFirstAndLastnameInpText = inscriptionFirstAndLastnameInp.text.toString().trim()
                inscriptionPseudonymeInpText = inscriptionPseudonymeInp.text.toString().trim()
                inscriptionPasswordInpText = inscriptionPasswordInp.text.toString().trim()
                inscriptionConfirmPasswordInpText = inscriptionConfirmPasswordInp.text.toString().trim()

                if (inscriptionFirstAndLastnameInpText.isNotEmpty() && inscriptionPseudonymeInpText.isNotEmpty() && inscriptionPasswordInpText.isNotEmpty() && inscriptionConfirmPasswordInpText.isNotEmpty()){

                    inscriptionPasswordInp.setError(null)
                    inscriptionConfirmPasswordInp.setError(null)
                    inscriptionBtnConnection.isEnabled = true

                }else{
                    inscriptionBtnConnection.isEnabled = false
                }


            }

            override fun afterTextChanged(s: Editable?) {
            }


        }

        inscriptionFirstAndLastnameInp.addTextChangedListener(inscriptionWatcherText)
        inscriptionPseudonymeInp.addTextChangedListener(inscriptionWatcherText)
        inscriptionPasswordInp.addTextChangedListener(inscriptionWatcherText)
        inscriptionConfirmPasswordInp.addTextChangedListener(inscriptionWatcherText)

        inscriptionBtnConnection.setOnClickListener {

            if (!PASSWORD_PATTERN.matcher(inscriptionPasswordInpText).matches()) {

                inscriptionPasswordInp.setError("Mot de passe trop faible")
                Toast.makeText(this@MainActivity2, "Votre mot de passe doit contenir au moins six (6) caractères" +
                        "avec minimum, un caractère spéciale, une lettre capitale et un chiffre", Toast.LENGTH_LONG).show()

                inscriptionBtnConnection.isEnabled = false

            } else {

                if (inscriptionPasswordInpText.equals(inscriptionConfirmPasswordInpText)) {

                    popupDialog.dismiss()
                    opendashboard()

                } else {

                    Toast.makeText(this@MainActivity2, "Veuillez confirmer votre mot de passe ", Toast.LENGTH_LONG).show()
                    inscriptionConfirmPasswordInp.setError("Mot de passe non conforme")
                    inscriptionBtnConnection.isEnabled = false

                }



            }

        }

        popupDialog.show()
    }

}
