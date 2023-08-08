package com.example.favouritegitrepo.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity


fun openLinkInChrome(context: Context, url: String) {
    val chromePackageName = "com.android.chrome"
    val chromeIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    chromeIntent.`package` = chromePackageName

    if (chromeIntent.resolveActivity(context.packageManager) != null) {
        // Chrome is installed, open the link in Chrome
        startActivity(context, chromeIntent, null)
    } else {
        // Chrome is not installed, open the link in the default browser
        startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse(url)), null)
    }
}


 fun shareUrl(context: Context, url: String) {
    val sendIntent = Intent(Intent.ACTION_SEND)
    sendIntent.type = "text/plain"
    sendIntent.putExtra(Intent.EXTRA_TEXT, url)

    // Create a chooser to display all apps capable of handling the share action
    val chooser = Intent.createChooser(sendIntent, "Share URL via...")

    if (sendIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(chooser)
    } else {
        Toast.makeText(context, "No apps installed to share this link!", Toast.LENGTH_SHORT).show()
    }
}



