package com.example.wallcraft.utils

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.text.Editable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.view.WindowCompat
import androidx.core.view.ancestors
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import coil.request.CachePolicy
import com.crazylegend.kotlinextensions.context.color
import com.crazylegend.kotlinextensions.views.color
import com.crazylegend.kotlinextensions.views.colorStateList
import com.crazylegend.kotlinextensions.views.disable
import com.crazylegend.kotlinextensions.views.gone
import com.crazylegend.kotlinextensions.views.visible
import com.crazylegend.kotlinextensions.whether
import com.example.wallcraft.R
import com.example.wallcraft.data.models.detail.ResponseDetail
import com.example.wallcraft.data.models.home.CategoryModel
import com.example.wallcraft.databinding.CustomSnackbarBinding

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.DecimalFormat


fun ImageView.loadImageByBlurHash(url: String, blurHashString: BitmapDrawable) {
    this.load(url) {
        placeholder(blurHashString)
        error(blurHashString)
        crossfade(true)
        crossfade(500)
        diskCachePolicy(CachePolicy.ENABLED)
    }
}
fun ImageView.loadBlurImage( blurHashString: BitmapDrawable) {
    this.load(blurHashString) {
        crossfade(true)
        crossfade(500)
        diskCachePolicy(CachePolicy.ENABLED)
    }
}


fun Fragment.launchLifeCycleScope(scope: suspend CoroutineScope.() -> Unit) =
    lifecycleScope.launch { scope() }


fun ImageView.changeTint(@ColorRes color: Int) =
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(color(color)))

fun FragmentActivity.statusBarIconColor(dark: Boolean) {
    window.apply {

        WindowCompat.getInsetsController(this, this.decorView).apply {
            isAppearanceLightStatusBars = dark
        }
    }
}

infix fun <T> T.isBiggerThan(that: T): Boolean where T : Comparable<T>, T : Number = this > that
infix fun <T> T.isLessThan(that: T): Boolean where T : Comparable<T>, T : Number = this < that

val String.toEditable: Editable get() = Editable.Factory.getInstance().newEditable(this)

fun ImageView.loadImage(url: String) {
    load(url) {
        crossfade(true)
        crossfade(500)
        diskCachePolicy(CachePolicy.ENABLED)

    }
}
fun View.isVisible(isShowLoading: Boolean, container: View) {
    isShowLoading.whether({
        //true
        this.visible()
        container.gone()
    }, {
        //false
        this.gone()
        container.visible()

    })
}

fun View.infiniteSnackBar(
    title: String,
    actionName: String,
    action: () -> Unit,
    @ColorRes actionColor: Int,
    swipeDismiss: Boolean = false,
    view: View

) {
    val snackBar = Snackbar.make(this, title, Snackbar.LENGTH_INDEFINITE)
        .setAction(actionName) { action() }
        .setActionTextColor(context.color(actionColor)).setAnchorView(view)

    // Set behavior to prevent swipe dismissal
    val noSwipeSnackBar = object : BaseTransientBottomBar.Behavior() {
        override fun canSwipeDismissView(child: View): Boolean {
            return swipeDismiss
        }
    }
    snackBar.behavior = noSwipeSnackBar

    snackBar.show()
}

fun ChipGroup.setupChip(list: MutableList<CategoryModel>) {
    list.forEach {
        val state = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf()),
            intArrayOf(color(R.color.outerSpace), color(R.color.lotion))
        )
        val drawable =
            ChipDrawable.createFromAttributes(context, null, 0, R.style.darkChip)
        Chip(context).also { chip ->
            chip.setChipDrawable(drawable)
            chip.text = it.name
            chip.setTextColor(state)
            chip.textSize = 16f
            chip.tag = it.id
            chip.chipStrokeWidth = .7f
            chip.chipStrokeColor =
                colorStateList(R.color.graniteGray)
            addView(chip)
        }
    }
}
fun ChipGroup.setupTagsChip(list: List<ResponseDetail.TagsPreview>) {
    list.forEach {

        val drawable =
            ChipDrawable.createFromAttributes(context, null, 0, R.style.grayChip)
        Chip(context).also { chip ->
            chip.disable()
            chip.setChipDrawable(drawable)
            chip.text = it.title

            chip.setTextColor(color(R.color.lotion))
            chip.textSize = 16f
            chip.chipStrokeWidth = .7f
            chip.chipStrokeColor = colorStateList(R.color.graniteGray)
            addView(chip)
        }
    }
}


fun ChipGroup.autoSelectChip(chipText: String?) {
    for (i in 0 until childCount) {
        val chip = getChildAt(i) as? Chip
        if (chip != null && chipText != null && chip.text == chipText) {
            chip.isChecked = true
            break
        }
    }
}
fun Fragment.popBackStack()=findNavController().popBackStack()
val Int.commaSeparator: String get() = DecimalFormat("#,###").format(this)





