package com.example.polizasliver.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.polizasliver.R
import com.example.polizasliver.data.model.TermsModel
import com.example.polizasliver.databinding.CardDetailInsuranceBinding
import com.example.polizasliver.ui.dialogs_alerts.DialogsAlerts

class TakeOutInsuranceAdapter(
    val context: Context,
    private val listTerms: List<TermsModel>,
    private val listener: TypesInsuranceActives
) : RecyclerView.Adapter<TakeOutInsuranceAdapter.CardViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewHolder {
        return CardViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_detail_insurance,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(context, listTerms[position], listener)
    }

    override fun getItemCount(): Int {
        return listTerms.size
    }

    class CardViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val binding = CardDetailInsuranceBinding.bind(itemview)
        fun bind(context: Context, termsModel: TermsModel, listener: TypesInsuranceActives) {
            binding.tvCardDetailInsurance.text = termsModel.typeTerms
            binding.ivCardDetailInsuranceIcon.setBackgroundResource(termsModel.iconTerms)
            binding.cvDetailInsurance.setOnClickListener {
                DialogsAlerts().warningAlet(
                    binding.root.context,
                    termsModel.typeTerms,
                    termsModel.clauses,
                    false,
                    context.getString(R.string.accept),
                    context.getString(R.string.cancel)
                ) {
                    binding.root.setBackgroundResource(R.drawable.shape_border_green)
                    if (termsModel.typeTerms.equals(context.getString(R.string.coverage))) {
                        listener.ItemTypeActives(true, false, false, false)
                    }
                    if (termsModel.typeTerms.equals(context.getString(R.string.exclusions))) {
                        listener.ItemTypeActives(false, true, false, false)
                    }
                    if (termsModel.typeTerms.equals(context.getString(R.string.deductibles))) {
                        listener.ItemTypeActives(false, false, true, false)
                    }
                    if (termsModel.typeTerms.equals(context.getString(R.string.payments))) {
                        listener.ItemTypeActives(false, false, false, true)
                    }
                }
            }
        }

    }
}