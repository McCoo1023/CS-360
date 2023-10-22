# CS-360

App Requirements and Goals:
The app developed is an inventory management application designed to aid users in tracking items and their quantities. The primary goal was to create a user-friendly interface where users can easily add, update, or delete items, and be notified when the quantity of any item falls below a specific threshold. This app addresses the need for simple, efficient inventory management without any hassle.

UI Design for User Needs:
The app includes various screens such as the login screen, registration screen, the main inventory grid, and a screen to add or update items. Features like intuitive buttons for adding, updating, and deleting items, along with notifications for low inventory, were integrated to enhance user experience. The UI was designed to be intuitive and user-friendly, making inventory management a breeze. The success of the design lies in its simplicity and the ease with which users can navigate and manage their inventory.

Coding Approach:
The coding process was approached with careful planning, ensuring a clean, organized codebase. Utilization of an ItemAdapter class for managing item views, implementing Parcelable for easy data transfer between activities, and handling user interactions efficiently are some of the techniques employed. These strategies facilitated a structured development process which can be replicated in future projects for efficiency and maintainability.

Testing and Validation:
Ensuring code functionality was achieved through rigorous testing using the Android Emulator. This phase was crucial for identifying bugs, ensuring that the user-interface behaved as expected, and that notifications were triggered correctly. This process revealed areas of improvement, especially around user notification when inventory was low.

Innovation to Overcome Challenges:
A notable challenge was ensuring the app functioned well regardless of user permission for sending SMS notifications. Innovating a fallback mechanism to handle the denial of SMS permissions while ensuring the app remained functional was a learning curve.

Demonstration of Knowledge:
Particularly, the implementation of SMS notifications to alert users of low inventory levels showcased an understanding of Android permissions and system services. This component not only fulfilled a user need but also demonstrated the capability to integrate Android features effectively into the app. This experience has been instrumental in understanding the importance of permissions and user-centric notifications in enhancing app usability and user satisfaction.
