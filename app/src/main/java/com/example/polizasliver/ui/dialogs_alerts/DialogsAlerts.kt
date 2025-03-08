package com.example.polizasliver.ui.dialogs_alerts

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import com.example.polizasliver.databinding.DialogSuccessBinding

class DialogsAlerts() {

    fun succesDialog(
        context: Context,
        noInsurance: String,
        clientName: String,
        callback: (() -> Unit)
    ) {
        val binding: DialogSuccessBinding =
            DialogSuccessBinding.inflate(LayoutInflater.from(context))
        var dialogSuccess = Dialog(context)
        dialogSuccess.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogSuccess.setCancelable(false)
        dialogSuccess.setContentView(binding.root)
        dialogSuccess.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        binding.dialogSuccessNoInsurance.text = noInsurance
        binding.tvDialogSuccessName.text = clientName

        binding.btnDialogSuccess.setOnClickListener {
            callback.invoke()
            dialogSuccess.dismiss()
        }

        dialogSuccess.show()
    }

    fun warningAlert(
        context: Context,
        tittle: String,
        message: String,
        cancelable: Boolean,
        buttomPositive: String,
        buttomNegative: String,
        callback: (() -> Unit)
    ) {
        val warningAlert = AlertDialog.Builder(context).create()
        warningAlert.setTitle(tittle)
        warningAlert.setMessage(message)
        warningAlert.setCancelable(cancelable)
        warningAlert.setButton(
            AlertDialog.BUTTON_POSITIVE,
            buttomPositive
        ) { dialog, which ->
            callback.invoke()
            warningAlert.dismiss()
        }

        warningAlert.setButton(
            AlertDialog.BUTTON_NEGATIVE,
            buttomNegative
        ) { dialog, which ->
            warningAlert.dismiss()
        }
        warningAlert.show()
    }


    fun alertWithouNegative(
        context: Context,
        tittle: String,
        message: String,
        cancelable: Boolean,
        buttomPositive: String
    ) {
        val warningAlert = AlertDialog.Builder(context).create()
        warningAlert.setTitle(tittle)
        warningAlert.setMessage(message)
        warningAlert.setCancelable(cancelable)
        warningAlert.setButton(
            AlertDialog.BUTTON_POSITIVE,
            buttomPositive
        ) { dialog, which ->
            warningAlert.dismiss()
        }
        warningAlert.show()
    }

    fun infoAlert(
        context: Context,
        tittle: String,
        message: String,
        cancelable: Boolean,
        buttomPositive: String,
        buttomNegative: String,
        callback: (() -> Unit)
    ) {
        val warningAlert = AlertDialog.Builder(context).create()
        warningAlert.setTitle(tittle)
        warningAlert.setMessage(message)
        warningAlert.setCancelable(cancelable)
        warningAlert.setButton(
            AlertDialog.BUTTON_POSITIVE,
            buttomPositive
        ) { dialog, which ->
            callback.invoke()
            warningAlert.dismiss()
        }

        warningAlert.setButton(
            AlertDialog.BUTTON_NEGATIVE,
            buttomNegative
        ) { dialog, which ->
            warningAlert.dismiss()
        }
        warningAlert.show()
    }


}