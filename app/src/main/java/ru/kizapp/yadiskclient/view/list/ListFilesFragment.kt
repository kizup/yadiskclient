package ru.kizapp.yadiskclient.view.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yandex.disk.rest.json.Resource
import kotlinx.android.synthetic.main.fragment_list_files.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kizapp.yadiskclient.R
import ru.kizapp.yadiskclient.di.MAIN_VIEW_MODEL
import ru.kizapp.yadiskclient.view.base.BaseFragment
import ru.kizapp.yadiskclient.viewmodel.list.ListFilesViewModel
import ru.kizapp.yadiskclient.viewmodel.main.MainContainerViewModel

class ListFilesFragment : BaseFragment() {

    companion object {
        private const val DIR_ARG = "dir_arg"

        fun newInstance(dir: String): ListFilesFragment {
            val fragment = ListFilesFragment()
            val bundle = Bundle()
            bundle.putString(DIR_ARG, dir)
            fragment.arguments = bundle
            return fragment
        }
    }

    lateinit var mCurrentDir: String
    private val mListFilesViewModel: ListFilesViewModel by viewModel()
    private val mMainViewModel: MainContainerViewModel by viewModel(name = MAIN_VIEW_MODEL)

    private val mFilesAdapter = FilesAdapter(object : OnResourceClicked {
        override fun onItemClick(resource: Resource) {
            mListFilesViewModel.resourceSelected(resource)
        }
    })

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mCurrentDir = arguments?.getString(DIR_ARG)!!
        mListFilesViewModel.mCurrentDir = mCurrentDir
    }

    override fun getLayoutId(): Int = R.layout.fragment_list_files

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvFiles.adapter = mFilesAdapter
        rvFiles.layoutManager = LinearLayoutManager(mBaseActivity)
        rvFiles.setHasFixedSize(true)

        mListFilesViewModel.mFilesLiveData.observe(this, Observer {
            mFilesAdapter.mFiles.clear()
            mFilesAdapter.mFiles.addAll(it.resourceList.items)
            mFilesAdapter.notifyDataSetChanged()
        })
        mListFilesViewModel.loadFiles()
    }

    override fun onResume() {
        super.onResume()
        mMainViewModel.setTitle(mCurrentDir)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mListFilesViewModel.onBackPressed()
    }

}