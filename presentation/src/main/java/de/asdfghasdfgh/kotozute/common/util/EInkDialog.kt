package de.asdfghasdfgh.kotozute.common.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import de.asdfghasdfgh.kotozute.R

/**
 * An AlertDialog drawn as STYLE.md's `EInkDialog`: no scrim, no animation, a white frame with a
 * 2dp rim of ink and a 12dp corner. Every slot of AlertDialog's builder is kept, so a dialog
 * moves over by changing its first line. See the `EInkDialog` style.
 */
fun Context.einkDialog(): AlertDialog.Builder = AlertDialog.Builder(this, R.style.EInkDialog)
