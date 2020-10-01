package ir.vahidmohammadisan.mobisoft.ui.base

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private var viewBinding: ViewDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = DataBindingUtil.getBinding(view)
    }

    override fun onDestroyView() {
        super<Fragment>.onDestroyView()
        viewBinding?.unbind()
        viewBinding = null
    }

    protected fun <B : ViewDataBinding> viewBinding(): B =
        @Suppress("UNCHECKED_CAST")
        requireNotNull(viewBinding as B) {
            "View is not a data binding layout"
        }

}