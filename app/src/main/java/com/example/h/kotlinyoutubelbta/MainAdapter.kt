package com.example.h.kotlinyoutubelbta

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    val videoTitles = listOf("First title","Second", "3rd", "MOOOOORE TITLE")

    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // how do we even create a view
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }
    /*TODO: Using synthetic properties inside (by ref the layout id directly) causing view to be looked up each time onBindViewHolder is called.
        Fix
        1. Look view in ViewHolder and store in variable or
        2. Use Kotlin Android Extension's LayoutContainer interface (Keep it in onBindViewHolder like in video)
     */

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        val videoTitle = videoTitles.get(position)
        val video = homeFeed.videos.get(position)
        holder?.view?.textView_video_title?.text = video.name

        holder?.view?.textView_channel_name?.text = video.channel.name + "  •  " + "20K Views\n4 days ago"

        val thumbnailImageView = holder?.view?.imageView_video_thumbnail
        //context it the entire activity. Load the the current video image into image view
        Picasso.with(holder?.view?.context).load(video.imageUrl).into(thumbnailImageView)

        val channelProfileImageView = holder?.view?.imageView_channel_profile
        Picasso.with(holder?.view?.context).load(video.channel.profileImageUrl).into(channelProfileImageView)

        holder?.video = video
    }

}

class CustomViewHolder(val view : View, var video: Video? = null) : RecyclerView.ViewHolder(view){

    companion object{
        val VIDEO_TITLE_KEY = "VIDEO_TITLE"
        val VIDEO_ID_KEY = "VIDEO_ID"
    }
    init {
        view.setOnClickListener{
            val intent = Intent(view.context, CourseDetailActivity::class.java)

            //When video is clicked it takes user to activity with correct nav tittle, # of videos
            intent.putExtra(VIDEO_TITLE_KEY, video?.name)
            intent.putExtra(VIDEO_ID_KEY, video?.id)

            view.context.startActivity(intent)
        }
    }
}