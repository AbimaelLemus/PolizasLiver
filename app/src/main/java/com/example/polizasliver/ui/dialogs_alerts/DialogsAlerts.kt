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

    fun succesDialog(context: Context) {
        val binding: DialogSuccessBinding =
            DialogSuccessBinding.inflate(LayoutInflater.from(context))
        var dialogSuccess = Dialog(context)
        dialogSuccess.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogSuccess.setCancelable(false)
        dialogSuccess.setContentView(binding.root)
        dialogSuccess.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        binding.btnDialogSuccess.setOnClickListener {
            dialogSuccess.dismiss()
        }

        dialogSuccess.show()


    }

    fun typeInsuranceDialog(context: Context, callback: ((typeinsurance: String) -> Unit)) {
        /*DialogsAlerts().typeInsuranceDialog(this@HomeInsuranceActivity) { typeinsurance ->
            Log.e(TAG, "onCreate: $typeinsurance")
        }*/
        val binding: DialogSuccessBinding =
            DialogSuccessBinding.inflate(LayoutInflater.from(context))
        var dialogSuccess = Dialog(context)
        dialogSuccess.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogSuccess.setCancelable(false)
        dialogSuccess.setContentView(binding.root)
        dialogSuccess.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        binding.btnDialogSuccess.setOnClickListener {
            dialogSuccess.dismiss()
            callback("algo")
        }

        dialogSuccess.show()


    }

    fun warningAlet(
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