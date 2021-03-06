package nanicky.losties.android.core.extensions

import nanicky.losties.android.R
import nanicky.losties.android.core.base.BaseActivity
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import nanicky.losties.android.core.base.Localizer


fun Activity.provideProgressDialog(): AlertDialog =
    AlertDialog.Builder(this).apply {
        setCancelable(false)
        setView(R.layout.layout_loading_dialog)
    }.create()


fun Context.showInfoAlertDialog(
    l: Localizer,
    title: String,
    message: String,
    positiveAction: (DialogInterface, Int) -> Unit = { _, _ -> },
    onCancel: () -> Unit = {}
) {
    val builder = AlertDialog.Builder(this)

    val dialog = builder
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(l.tr(R.string.i_understand), positiveAction)
        .setOnCancelListener { onCancel() }
        .create()

    dialog.setTypefaceInDialog(this)
    dialog.show()
}

fun Context.showQuestionAlertDialog(
    l: Localizer,
    title: String,
    message: String,
    positiveAction: (DialogInterface, Int) -> Unit,
    negativeAction: (DialogInterface, Int) -> Unit = {_,_ -> }
) {
    val builder = AlertDialog.Builder(this)

    val dialog = builder
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(l.tr(R.string.yes), positiveAction)
        .setNegativeButton(l.tr(R.string.cancel), negativeAction)
        .show()

    dialog.show()
    dialog.setTypefaceInDialog(this)
}



fun Activity.provideBottomSheetTimePicker(block: (Pair<Int, Int>) -> Unit) {
    val dialog = BottomSheetDialog(this)
    val dialogContentView = layoutInflater.inflate(R.layout.bottom_sheet_time_chooser, null)
    dialog.setContentView(dialogContentView)

    /*dialogContentView.tpNotificationTime.setIs24HourView(true)

    dialogContentView.btnSave.setOnClickListener {
        block(dialogContentView.tpNotificationTime.currentHour to
                dialogContentView.tpNotificationTime.currentMinute)

        dialog.dismiss()
    }*/


    val behavior = BottomSheetBehavior.from(dialogContentView.parent as View)
    behavior.peekHeight = resources.getDimension(R.dimen.space700).toInt()

    dialog.show()
}



