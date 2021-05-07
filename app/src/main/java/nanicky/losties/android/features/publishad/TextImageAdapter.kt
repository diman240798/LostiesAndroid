package nanicky.losties.android.features.publishad

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import nanicky.losties.android.R

class TextImageAdapter(
    context: Context,
    val items: List<TextImageAnimalType>,
    val texts: List<String> = items.map { it.text }
) : ArrayAdapter<String>(context, R.layout.dropdown_image_text_item, R.id.text, texts) {

    val lInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // используем созданные, но не используемые view
        var view = convertView
        if (view == null) {
            view = lInflater.inflate(R.layout.dropdown_image_text_item, parent, false)!!
        }

        // заполняем View в пункте списка данными

        val item = getMyItem(position)

        view.findViewById<ImageView>(R.id.image).visibility = View.GONE
        view.findViewById<TextView>(R.id.text).text = item.text


        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        // используем созданные, но не используемые view
        var view = convertView
        if (view == null) {
            view = lInflater.inflate(R.layout.dropdown_image_text_item, parent, false)!!
        }

        // заполняем View в пункте списка данными

        val item = getMyItem(position)

        view.findViewById<ImageView>(R.id.image).setImageResource(item.animalType.image)
        view.findViewById<TextView>(R.id.text).text = item.text


        return view
    }

    fun getItemImage(position: Int): Int {
        return getMyItem(position).animalType.image
    }

    fun getMyItem(position: Int): TextImageAnimalType {
        val itemText = getItem(position)
        return items[texts.indexOf(itemText)]
    }
}
