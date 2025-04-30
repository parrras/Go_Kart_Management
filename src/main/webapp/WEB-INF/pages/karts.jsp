<%-- karts.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Our Karts</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/karts.css">
</head>
<body>
    <header>
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/resources/images/Screenshot_2025-04-14_230710-removebg-preview.png" alt="Go Kart Racing Logo">
        </div>
        <nav>
            <ul class="nav-links">
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
            	<li><a href="${pageContext.request.contextPath}/karts">Karts</a></li>
            	<li><a href="${pageContext.request.contextPath}/pricing">Pricing</a></li>
            	<li><a href="${pageContext.request.contextPath}/contactUs">Contact Us</a></li>
            	<li><a href="${pageContext.request.contextPath}/activities">Activities</a></li>
            	<li><a href="${pageContext.request.contextPath}/logout">Log Out</a></li>
                <li><a href="#" class="book-now-button">Book Now</a></li>
            </ul>
        </nav>
    </header>

    <main class="karts-page">
        <section class="karts-section">
            <div class="karts-container">
                <h2>The Karts</h2>
                <div class="kart-display">
                    <div class="kart-image-container">
                        <img id="kart-image" src="${pageContext.request.contextPath}/resources/images/kart1.jpg" alt="Standard Kart">
                    </div>
                    <div class="kart-buttons">
                        <button data-kart="kart1">Prev</button>
                        <span id="kart-name">Standard Kart</span>
                        <button data-kart="kart2">Next</button>
                    </div>
                </div>
                <div class="kart-description">
                    <p id="kart-description-text">
                        Perfect for beginners, easy to handle.
                    </p>
                </div>
                <div class="kart-info-boxes">
                    <div class="info-box">
                        <h3>Age & Height Restrictions</h3>
                        <p id="age-height-restrictions">
                            
                        </p>
                    </div>
                    <div class="info-box">
                        <h3>Disability or Medical Condition</h3>
                        <p id="disability-medical-conditions">
                            
                        </p>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <footer>
        <p>&copy; ${java.time.Year.now()} Go Kart Racing. All rights reserved.</p>
    </footer>

    <script>
        const karts = [
            {
                name: "Standard Kart",
                image: "${pageContext.request.contextPath}/resources/images/Screenshot_2025-04-14_232252-removebg-preview.png",
                description: "Perfect for beginners and easy to handle, our Standard Kart is built for pure fun and comfort. With smooth steering and stable control, it's ideal for casual racers and first-time drivers. Designed with key safety features, it ensures a secure ride for all age groups. Whether you're racing solo or with friends, it delivers an exciting go-karting experience without the need for prior experience. Just hop in and enjoy the thrill!",
                ageHeight: "Our Standard Kart is designed for beginner and casual racers looking for a fun and easy ride. It’s simple to control and perfect for building confidence on the track. To drive a Standard Kart, participants must be at least 10 years old and a minimum of 4 feet (122 cm) tall. This ensures drivers can comfortably reach the pedals and steering wheel while remaining securely fastened with the safety harness. As with all karts, helmets and closed-toe shoes are mandatory. While this kart is beginner-friendly, it still offers the thrill of real racing—so please follow all track safety rules and staff instructions.",
                disability: "We welcome riders of all abilities and aim to provide a safe and inclusive environment. If a driver has a disability or medical condition, such as limited mobility, sensory impairments, or chronic health concerns, we encourage you to speak with a team member before booking your session. While the Standard Kart is built for easy handling, certain conditions—like heart issues, epilepsy, recent surgeries, pregnancy, or back and neck problems—may pose safety risks and could restrict participation. Our staff will assess each situation individually and provide recommendations or alternatives if needed."
            },
            {
                name: "Pro Kart",
                image: "${pageContext.request.contextPath}/resources/images/Screenshot_2025-04-14_233010-removebg-preview.png",
                description: "For experienced racers, higher speeds and sharper control, the Pro Kart is engineered for peak performance. With a powerful engine, precision handling, and rapid acceleration, it’s built to dominate the track. Ideal for those who crave a competitive edge and real racing intensity, this kart delivers an unmatched adrenaline rush. Advanced safety features ensure control even at top speeds, making every lap fast, thrilling, and secure.",
                ageHeight: "Our Pro Kart is designed for adrenaline seekers and skilled drivers who crave high-speed action. With enhanced speed, sharp cornering, and responsive handling, it delivers a more intense racing experience. To operate a Pro Kart, you must be at least 16 years old and 5 feet (152 cm) or taller to safely control the vehicle. This kart is not intended for beginners, and we recommend prior go-karting experience before hopping in.",
                disability: "Due to the physical demands and speed of the Pro Kart, riders with heart conditions, neck or back injuries, pregnancy, epilepsy, or other serious medical concerns are strongly discouraged from participating. Additionally, individuals with certain disabilities may find the Pro Kart difficult to operate. Our team is available on-site to assess your situation and recommend a suitable kart option. Safety gear including helmets and gloves must be worn at all times, and all drivers must attend a mandatory safety briefing.",
            },
            {
                name: "Double Kart",
                image: "${pageContext.request.contextPath}/resources/images/Screenshot_2025-04-14_232210-removebg-preview.png",
                description: "Bring a friend for twice the fun! Our Double Kart is perfect for pairs — whether it’s a parent and child, siblings, or best buds looking to ride together. With side-by-side seating and easy controls, it offers a safe, comfortable, and exciting experience for two. It's the ideal choice for those who want to share the thrill of the track without needing separate karts. Fun, bonding, and speed — all in one ride!",
                ageHeight: "To ensure a safe and enjoyable racing experience for everyone, we have specific age and height requirements in place. Riders must be at least 10 years old and a minimum of 4 feet (122 cm) tall to operate a kart. For our Junior Karts, participants between the ages of 10 and 15 can race under supervision. Adult Karts are reserved for those 16 years and older with adequate driving capability. Additionally, all drivers must wear a helmet and closed-toe shoes at all times while on the track. We prioritize safety, so these guidelines are strictly enforced. Please reach out to our staff if you’re unsure which kart is suitable for you or your child.",
                disability: "At Go Kart Racing, we’re committed to making our experience inclusive and accessible for all guests. If you or your passenger has a disability or medical condition, we strongly encourage you to speak with our on-site staff prior to racing. While many conditions do not prevent participation, certain medical issues—such as heart problems, epilepsy, recent surgeries, pregnancy, or severe back/neck conditions—may pose safety risks and require medical clearance or may prevent you from racing altogether. We evaluate every situation individually to ensure the highest standards of safety and comfort.",
            },
            {
                name: "Electric Kart",
                image: "${pageContext.request.contextPath}/resources/images/Screenshot_2025-04-15_190852-removebg-preview%20(1).png",
                description: "Environment friendly, silent and fast, our Electric Kart offers a smooth and powerful ride without the noise or emissions. Perfect for indoor tracks and eco-conscious racers, it delivers instant torque, responsive handling, and a futuristic feel. With zero exhaust and low maintenance, it's a smart choice for a cleaner and quieter racing experience — all while keeping the thrill alive.",
                ageHeight: "Our Electric Karts offer a smooth, environmentally-friendly racing experience without compromising the thrill. Perfect for kids aged 8 and up and adults alike, these karts are quieter and easier to handle, making them a popular choice for beginners and families. The minimum height requirement is 4 feet (122 cm), and all drivers must be able to sit securely and reach the controls.",
                disability: "Electric Karts are ideal for those who may be sensitive to noise or vibrations, and they offer gradual acceleration for a more controlled experience. They may also be a safer option for individuals with mild disabilities or manageable medical conditions, though we recommend informing our staff ahead of time. Conditions such as recent surgeries, epilepsy, or chronic back problems should still be disclosed. Our staff is trained to offer assistance and adjustments to ensure every guest enjoys a safe and comfortable race. As always, protective gear is required, and safety guidelines must be followed.",
            }
        ];

        let currentKartIndex = 0;
        const kartImage = document.getElementById("kart-image");
        const kartName = document.getElementById("kart-name");
        const kartDescriptionText = document.getElementById("kart-description-text");
        const ageHeightRestrictionsText = document.getElementById("age-height-restrictions");
        const disabilityMedicalConditionsText = document.getElementById("disability-medical-conditions");
        const prevButton = document.querySelector("[data-kart='kart1']");
        const nextButton = document.querySelector("[data-kart='kart2']");


        function updateKartInfo() {
            kartImage.src = karts[currentKartIndex].image;
            kartImage.alt = karts[currentKartIndex].name;
            kartName.textContent = karts[currentKartIndex].name;
            kartDescriptionText.textContent = karts[currentKartIndex].description;
            ageHeightRestrictionsText.textContent = karts[currentKartIndex].ageHeight;
            disabilityMedicalConditionsText.textContent = karts[currentKartIndex].disability;
        }

        prevButton.addEventListener("click", () => {
            currentKartIndex = (currentKartIndex - 1 + karts.length) % karts.length;
            updateKartInfo();
             if (currentKartIndex === 0) {
                prevButton.dataset.kart = 'kart1';
                nextButton.dataset.kart = 'kart2';
             }
             else if(currentKartIndex === 1){
                prevButton.dataset.kart = 'kart1';
                nextButton.dataset.kart = 'kart2';
             }
             else if(currentKartIndex === 2){
                prevButton.dataset.kart = 'kart2';
                nextButton.dataset.kart = 'kart4';
             }
             else{
                prevButton.dataset.kart = 'kart3';
                nextButton.dataset.kart = 'kart1';
             }
        });

        nextButton.addEventListener("click", () => {
            currentKartIndex = (currentKartIndex + 1) % karts.length;
            updateKartInfo();
            if (currentKartIndex === 0) {
                prevButton.dataset.kart = 'kart1';
                nextButton.dataset.kart = 'kart2';
             }
             else if(currentKartIndex === 1){
                prevButton.dataset.kart = 'kart1';
                nextButton.dataset.kart = 'kart2';
             }
             else if(currentKartIndex === 2){
                prevButton.dataset.kart = 'kart2';
                nextButton.dataset.kart = 'kart4';
             }
             else{
                prevButton.dataset.kart = 'kart3';
                nextButton.dataset.kart = 'kart1';
             }
        });

        // Initial update
        updateKartInfo();
    </script>
</body>
</html>