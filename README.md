# 🎬 Movie Tickets Booking System

A user-friendly Java application for booking movie tickets with an intuitive GUI built using Swing.

## ✨ Features

- 🎥 Browse available movies with ratings and details
- 🎫 Interactive seat selection with real-time visualization
- 💰 Dynamic pricing based on seat type
- 🔐 Secure booking confirmation
- 📋 View booking history
- ⏱️ Progress indicator during operations
- 🎨 Modern and intuitive user interface

## 🏗️ Project Structure

```
movie-tickets-booking/
├── src/
│   ├── ui/
│   │   ├── MainFrame.java
│   │   ├── MovieListPanel.java
│   │   ├── SeatSelectionPanel.java
│   │   ├── BookingConfirmationPanel.java
│   │   ├── BookingHistoryPanel.java
│   │   └── ProgressDialog.java
│   ├── model/
│   │   ├── Movie.java
│   │   ├── Theater.java
│   │   ├── Seat.java
│   │   ├── Booking.java
│   │   └── User.java
│   ├── service/
│   │   ├── BookingService.java
│   │   ├── MovieService.java
│   │   └── PaymentService.java
│   ├── util/
│   │   └── ProgressTracker.java
│   └── App.java
├── pom.xml
└── README.md
```

## 📋 Requirements

- Java 11 or higher
- Maven 3.6+

## 🚀 How to Run

```bash
# Compile the project
javac -d bin src/**/*.java

# Run the application
java -cp bin App
```

## 📖 Usage

1. Launch the application
2. Browse and select a movie from the list
3. Choose your preferred seats from the interactive seat map
4. Review booking details and proceed to payment
5. Confirm your booking
6. View booking history anytime

## 🎨 UI Components

- **Main Frame**: Application window with tabbed interface
- **Movie List Panel**: Browse available movies
- **Seat Selection Panel**: Interactive seat selection with preview
- **Booking Confirmation**: Review and confirm booking
- **Progress Dialog**: Visual progress indicator with percentage
- **Booking History**: View all your bookings

## 📝 License

MIT License
