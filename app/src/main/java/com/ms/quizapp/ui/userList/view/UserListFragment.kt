package com.ms.quizapp.ui.userList.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ms.quizapp.R
import com.ms.quizapp.data.network.dataloadstatus.DataLoadStatusEnum
import com.ms.quizapp.data.network.models.UserDataList
import com.ms.quizapp.databinding.FragmentUserListBinding
import com.ms.quizapp.ui.userList.adapters.UserListRvAdapter
import com.ms.quizapp.ui.userList.viewmodels.UserListViewModel
import com.ms.quizapp.ui.userList.viewmodels.UserListViewModelFactory
import com.ms.quizapp.utils.*
import java.util.*

class UserListFragment : Fragment() {
    private var binding: FragmentUserListBinding? = null

    private var userListRvAdapter: UserListRvAdapter? = null
    private var arrUserList: MutableList<UserDataList> = ArrayList()

    private var userListViewModel: UserListViewModel? = null

    private var scrollListener: EndlessRecyclerViewScrollListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserListBinding.inflate(layoutInflater)

        userListViewModel = ViewModelProvider(
            this, UserListViewModelFactory(activity?.application!!)
        )[UserListViewModel::class.java]

        if (context?.isNetworkConnected()!!) {
            userListViewModel?.getUserList()
        } else {
            showPageError(context?.getString(R.string.no_internet_connection)!!)
        }

        userListViewModel?.userListLoadStatus?.observe(viewLifecycleOwner, Observer {
            when (it.dataLoadStatusEnum) {
                DataLoadStatusEnum.STARTED -> {
                    if (userListViewModel?.currentPage == 0) {
                        binding?.progressBar?.visible()
                        binding?.rvUserList?.gone()
                        binding?.llErrorLayout?.gone()
                        binding?.edtSearchUser?.gone()
                    }
                }
                DataLoadStatusEnum.SUCCESS -> {
                    setUserDataOnUi(it.data)
                }
                DataLoadStatusEnum.FAILED -> showPageError()
                else -> {
                }
            }
        })

        clickHandlers()
        setUserRecyclerView()



        return binding?.root
    }

    private fun setUserRecyclerView() {
        userListRvAdapter = UserListRvAdapter(context, arrUserList)
        binding?.rvUserList?.layoutManager = GridLayoutManager(context, 2)
        binding?.rvUserList?.adapter = userListRvAdapter

        scrollListener = object : EndlessRecyclerViewScrollListener(
            binding?.rvUserList?.layoutManager as GridLayoutManager
        ) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (binding?.edtSearchUser?.text.toString().isEmpty()) {
                    if (context?.isNetworkConnected()!!) {
                        if (userListViewModel?.hasMore!!) {
                            userListViewModel?.getUserList(
                                page = page
                            )
                        }
                    }
                }
                Log.d("onLoadMore", "onLoadMore: pageNo: $page")
            }
        }

        scrollListener?.let {
            binding?.rvUserList?.addOnScrollListener(it)
        }
    }

    private fun clickHandlers() {

        binding?.tvBtnRetry?.onSafeClick {
            if (context?.isNetworkConnected()!!) {
                scrollListener?.resetState()
                userListViewModel?.getUserList()
            } else {
                context?.showToast(getString(R.string.no_internet_connection))
            }
        }

        binding?.edtSearchUser?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (binding?.edtSearchUser?.text?.isNotEmpty()!!) {
                    filter(binding?.edtSearchUser?.text.toString())
                }else{
                    userListRvAdapter?.filteredList(arrUserList)
                }
            }

        })
    }

    fun filter(text: String) {
        if (arrUserList.isNotEmpty()) {
            val filterList: MutableList<UserDataList> = ArrayList()
            for (item in arrUserList) {
                if (item.firstName?.lowercase()
                        ?.contains(text.lowercase(Locale.getDefault()))!!
                ) {
                    filterList.add(item)
                }
            }
            userListRvAdapter?.filteredList(
                filterList
            )
        }
    }

    private fun setUserDataOnUi(data: MutableList<UserDataList>?) {
        if (userListViewModel?.currentPage == 0) {
            arrUserList.clear()
        }
        binding?.progressBar?.gone()
        binding?.rvUserList?.visible()
        if (!data.isNullOrEmpty()) {
            binding?.edtSearchUser?.visible()
            arrUserList.addAll(data)
            userListViewModel?.hasMore = true
        } else {
            userListViewModel?.hasMore = false
            if (userListViewModel?.currentPage == 0) {
                binding?.llErrorLayout?.visible()
                binding?.tvErrorMsg?.setText(getString(R.string.no_data_found))
            }
        }

    }

    private fun showPageError(errorMsg: String = getString(R.string.something_went_wrong)) {
        binding?.progressBar?.gone()
        if (userListViewModel?.currentPage == 0) {
            binding?.edtSearchUser?.gone()
            binding?.llErrorLayout?.visible()
            binding?.rvUserList?.gone()
            binding?.tvErrorMsg?.setText(errorMsg)
        } else {
            context?.showToast(errorMsg)
        }
    }


}