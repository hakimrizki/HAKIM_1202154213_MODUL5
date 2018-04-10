package id.hakimrizki.hakim_1202154213_modul5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hakimrizki on 22/03/18.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<TodoList> mTodoList;
    private Context mContext;
    private RecyclerView mRecyclerView;
    String warna;
    int id;

    public TodoListAdapter(List<TodoList> mTodoList, Context mContext, RecyclerView mRecyclerView) {
        this.mTodoList = mTodoList;
        this.mContext = mContext;
        this.mRecyclerView = mRecyclerView;
        mInflater = LayoutInflater.from(mContext);
    }

    public TodoListAdapter(List<TodoList> mTodoList, Context mContext) {
        this.mTodoList = mTodoList;
        this.mContext = mContext;
    }

    @Override
    public TodoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.activity_simple_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TodoListAdapter.ViewHolder holder, int position) {
        final TodoList todoList = this.mTodoList.get(position);
        holder.tvTodo.setText(todoList.getTodo());
        holder.tvDescription.setText(todoList.getDescription());
        holder.tvPriority.setText(todoList.getPriority());

        switch (warna){
            case "Merah":holder.bgColor.setBackgroundResource(R.color.colorAccent);break;
            case "Cyan":holder.bgColor.setBackgroundColor(Color.CYAN);break;
            case "Hijau":holder.bgColor.setBackgroundColor(Color.GREEN);break;
            case "Putih":holder.bgColor.setBackgroundColor(Color.WHITE);break;
        }
    }

    @Override
    public int getItemCount() {
        return mTodoList.size();
    }

    public void add(int position, TodoList todoList) {
        mTodoList.add(position, todoList);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        final TodoList todoList = mTodoList.get(position);
        DataHelper dbHelper = new DataHelper(mContext);
        dbHelper.deletePersonRecord(todoList.getId(), mContext);
        mTodoList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mTodoList.size());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTodo, tvDescription, tvPriority;
        LinearLayout bgColor;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTodo = (TextView) itemView.findViewById(R.id.tvTodo);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvPriority = (TextView) itemView.findViewById(R.id.tvPriority);
            bgColor = (LinearLayout) itemView.findViewById(R.id.layout_background);
            SharedPreferences Preference = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
            warna = Preference.getString("chosenColor","-1");


        }
    }
}
