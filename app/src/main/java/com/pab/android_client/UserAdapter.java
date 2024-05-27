package com.pab.android_client;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private Context context;
    private MainActivity mainActivity; // Referensi ke MainActivity

    // Konstruktor UserAdapter yang menerima List<User> dan Context
    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = userList.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());

        // Mengatur OnClickListener pada itemView untuk menangani tap pada item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof MainActivity) {
                    ((MainActivity) context).showUpdateDialog(user);
                }
            }
        });

        // Mengatur OnClickListener pada tombol delete untuk menangani tap pada tombol delete
//        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDeleteConfirmationDialog(user.getId());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    // Metode untuk menampilkan dialog konfirmasi penghapusan
//    private void showDeleteConfirmationDialog(final int userId) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Delete User");
//        builder.setMessage("Are you sure you want to delete this user?");
//
//        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                deleteUser(userId);
//            }
//        });
//
//        builder.setNegativeButton("Cancel", null);
//
//        builder.show();
//    }

    // Metode untuk menghapus pengguna dari daftar dan server
//    private void deleteUser(int userId) {
//        ApiService apiService = ApiClient.getClient().create(ApiService.class);
//            Call<Void> call = apiService.deleteUser(userId);
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(context, "User deleted successfully", Toast.LENGTH_SHORT).show();
//                    // Refresh user list after deletion
//                    if (mainActivity != null) {
//                        mainActivity.fetchUsers();
//                    }
//                } else {
//                    Toast.makeText(context, "Failed to delete user: " + response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Toast.makeText(context, "Failed to delete user: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    // ViewHolder untuk UserAdapter
    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView name, email;
        public Button buttonDelete;

        public UserViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            email = itemView.findViewById(R.id.textViewEmail);
//            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }

    // Metode untuk mengatur MainActivity yang terkait
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
