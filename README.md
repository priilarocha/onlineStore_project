# Mobile Apps 1 Project

- Student Name: Priscila Rocha de Queiroz
- Student ID: 25337
- Course: Science in Computing

## Requirement Checklist

- [x] 1. Authentication
    - [x] 1.1. Allow User to Signup
    - [x] 1.2. Log In using username and password
    - [x] 1.3. Store userID once logged in to keep the user logged in (even after restarting the app)
- [ ] 2. Product Listing
    - [ ] 2.1. List Product Categories
    - [ ] 2.2. On clicking a Category, list Products in that Category
    - [ ] 2.3. On clicking a Product, show Product description, show buy button and controls to change quantity.
- [ ] 3. Cart
    - [ ] 3.1. Show cart summary
    - [ ] 3.2. Show total amount
    - [ ] 3.3. Purchase button to place an order, show order notification
- [ ] 4. Show order history
    - [ ] 4.1. List users orders
    - [ ] 4.2. On clicking an Order, show Order details and Products ordered
    - [ ] 4.3. On clicking a Product, take them to Product description page created for 2.3
- [ ] 5. Show User details
    - [ ] 5.1. Use the stored userID to show user details
    - [ ] 5.2. Show a random circular profile image
    - [ ] 5.3. Show Logout button, on click take back to Signup / Log In page (Restart should not auto login after logout)
- [ ] 6. UI/Implementational Requirements
    - [x] 6.1. RecyclerView used for all Lists: Categories, Products, Orders
    - [ ] 6.2. If logged in, attach authentication token to all requests until logout
    - [ ] 6.3. Add a small "About this app" button in the profile page, that shows a page on click with your copyright details and credits
- [ ] 7. Bonus
    - [ ] 7.1. ViewPager2 with bottom TabLayout for: Shop, Cart, Orders, Profile icons
    - [ ] 7.2. Show a map fragment based on the GPS co-ordinates in the user profile


## Report

I had been struggling to communicate the app with the fake store api. I had tried and redone this project many times. Every time I changed the way I was creating the app, but always I was stopped in a different challenge.
In this last version I kept using the login technique we learnt on the 19/04 class, and the layouts were created.
I had created the recycler view layouts as well, the only problem is to connect it to the api.

## References and Credits

1. Shopping cart. URL: [Link](https://pusher.com/tutorials/shopping-cart-kotlin-part-1/#demo)
2. Duplicate class error. URL: [Link](https://stackoverflow.com/questions/75274720/a-failure-occurred-while-executing-appcheckdebugduplicateclasses)
3. OkHttp. URL: [Link](https://square.github.io/okhttp/recipes/)

## APK Releases

1. onlineStore_1.0 [link](https://github.com/priilarocha/onlineStore_project/releases/tag/onlineStore)