package com.sw.hong.listviewcontrolexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mItems = arrayListOf<TelItem>()

    private var mItem = arrayListOf<TelItem>()
    private var mItem2 = arrayListOf<TelItem>()
    private lateinit var mAdapter : HListAdapter
    private lateinit var mAdapter2 : HListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter =  HListAdapter(this,mItem)
        mAdapter2 =  HListAdapter(this,mItem2)
        listView.adapter = mAdapter
        listView2.adapter = mAdapter2
        //리스트뷰의 선택모드를 다중선택모드로 설정
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        listView2.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        //추가버튼 리스너 등록
        button_add.setOnClickListener {
            mItem.add(TelItem(editText.text.toString(), editText2.text.toString()))  // 아이템추가.
            mAdapter.notifyDataSetChanged()      // 어뎁터에 데이터 변경이 있음을 알림.
        }
        button_add2.setOnClickListener {
            mItem2.add(TelItem(editText.text.toString(), editText2.text.toString()))  // 아이템추가.
            mAdapter2.notifyDataSetChanged()      // 어뎁터에 데이터 변경이 있음을 알림.
        }
        //삭제버튼 리스너 등록
        button_del.setOnClickListener {
            val checkedItem = listView.checkedItemPositions

            //for문과 동일
            (mAdapter.count downTo 0)
                    .filter { checkedItem.get(it) }
                    .forEach { mItem.removeAt(it) }

            //선택초기화
            listView.clearChoices()
            //데이터가 변경됬음을 알림
            mAdapter.notifyDataSetChanged()


        }
        //삭제버튼 리스너 등록
        button_del2.setOnClickListener {
            val checkedItem = listView2.checkedItemPositions
            //for문과 동일
            (mAdapter2.count downTo 0)
                    .filter { checkedItem.get(it) }
                    .forEach { mItem2.removeAt(it) }
            listView2.clearChoices()
            //데이터가 변경됬음을 알림
            mAdapter2.notifyDataSetChanged()
        }


            //수정버튼 리스너 등록
        button_modified.setOnClickListener {
            val checkedItem = listView.checkedItemPositions
            //for문과 동일
            (mAdapter.count downTo 0)
                    .filter { checkedItem.get(it) }
                    .forEach { mItem[it].Name = editText.text.toString()
                               mItem[it].Phone = editText2.text.toString()
                    }
            mAdapter.notifyDataSetChanged()
        }
        //수정버튼 리스너 등록
        button_modified2.setOnClickListener {
            val checkedItem = listView2.checkedItemPositions
            //for문과 동일
            (mAdapter2.count downTo 0)
                    .filter { checkedItem.get(it) }
                    .forEach { mItem2[it].Name = editText.text.toString()
                        mItem2[it].Phone = editText2.text.toString()
                    }

            mAdapter2.notifyDataSetChanged()
        }

    }
}
