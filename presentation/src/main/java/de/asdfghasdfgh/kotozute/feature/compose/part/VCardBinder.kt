/*
 * Copyright (C) 2017 Moez Bhatti <moez.bhatti@gmail.com>
 *
 * This file is part of QKSMS.
 *
 * QKSMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * QKSMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with QKSMS.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.asdfghasdfgh.kotozute.feature.compose.part

import android.content.Context
import androidx.core.view.isVisible
import de.asdfghasdfgh.kotozute.R
import de.asdfghasdfgh.kotozute.common.base.QkViewHolder
import de.asdfghasdfgh.kotozute.common.util.Colors
import de.asdfghasdfgh.kotozute.common.util.ReceivedCard
import de.asdfghasdfgh.kotozute.common.util.extensions.getDisplayName
import de.asdfghasdfgh.kotozute.common.util.extensions.resolveThemeColor
import de.asdfghasdfgh.kotozute.common.util.extensions.setBackgroundTint
import de.asdfghasdfgh.kotozute.common.util.extensions.setTint
import de.asdfghasdfgh.kotozute.extensions.isVCard
import de.asdfghasdfgh.kotozute.extensions.mapNotNull
import de.asdfghasdfgh.kotozute.feature.compose.BubbleUtils
import de.asdfghasdfgh.kotozute.model.Message
import de.asdfghasdfgh.kotozute.model.MmsPart
import ezvcard.Ezvcard
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import de.asdfghasdfgh.kotozute.databinding.MmsVcardListItemBinding

class VCardBinder @Inject constructor(colors: Colors, private val context: Context) : PartBinder() {

    override val partLayout = R.layout.mms_vcard_list_item
    override var theme = colors.theme()

    override fun canBindPart(part: MmsPart) = part.isVCard()

    override fun bindPart(
        holder: QkViewHolder,
        part: MmsPart,
        message: Message,
        canGroupWithPrevious: Boolean,
        canGroupWithNext: Boolean
    ) {
        val binding = MmsVcardListItemBinding.bind(holder.itemView)
        BubbleUtils.getBubble(false, canGroupWithPrevious, canGroupWithNext, message.isMe())
                .let(binding.vCardBackground::setBackgroundResource)

        holder.containerView.setOnClickListener { clicks.onNext(part.id) }

        // Read the way the tap reads it (ReceivedCard), so a card kept in the message's text
        // column shows its name too, and one that cannot be read leaves the label alone
        // instead of taking the whole conversation down with it.
        Observable.fromCallable { ReceivedCard.clean(ReceivedCard.read(context, part.id)) ?: ByteArray(0) }
                .map { bytes -> if (bytes.isEmpty()) "" else (Ezvcard.parse(String(bytes, Charsets.UTF_8)).first()?.getDisplayName() ?: "") }
                .onErrorReturnItem("")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ displayName ->
                    binding.name?.text = displayName
                    binding.name.isVisible = displayName.isNotEmpty()
                }, { binding.name.isVisible = false })

        if (!message.isMe()) {
            binding.vCardBackground.setBackgroundTint(theme.theme)
            binding.vCardAvatar.setTint(theme.textPrimary)
            binding.name.setTextColor(theme.textPrimary)
            binding.label.setTextColor(theme.textTertiary)
        } else {
            binding.vCardBackground.setBackgroundTint(holder.containerView.context.resolveThemeColor(R.attr.bubbleColor))
            binding.vCardAvatar.setTint(holder.containerView.context.resolveThemeColor(android.R.attr.textColorSecondary))
            binding.name.setTextColor(holder.containerView.context.resolveThemeColor(android.R.attr.textColorPrimary))
            binding.label.setTextColor(holder.containerView.context.resolveThemeColor(android.R.attr.textColorTertiary))
        }
    }

}
