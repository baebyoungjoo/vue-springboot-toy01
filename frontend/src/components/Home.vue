<template>
  <div style="height: 590px;">
      <!-- swiper1 -->
      <swiper :options="swiperOptionTop" class="gallery-top" ref="swiperTop">
        <swiper-slide class="slide-1"></swiper-slide>
        <swiper-slide class="slide-2"></swiper-slide>
        <swiper-slide class="slide-3"></swiper-slide>
        <swiper-slide class="slide-4"></swiper-slide>
        <swiper-slide class="slide-5"></swiper-slide>
        <div class="swiper-button-next swiper-button-white" slot="button-next"></div>
        <div class="swiper-button-prev swiper-button-white" slot="button-prev"></div>
      </swiper>
      <!-- swiper2 Thumbs -->
      <swiper :options="swiperOptionThumbs" class="gallery-thumbs" ref="swiperThumbs">
        <swiper-slide class="slide-1"></swiper-slide>
        <swiper-slide class="slide-2"></swiper-slide>
        <swiper-slide class="slide-3"></swiper-slide>
        <swiper-slide class="slide-4"></swiper-slide>
        <swiper-slide class="slide-5"></swiper-slide>
      </swiper>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        swiperOptionTop: {
          spaceBetween: 30,
          centeredSlides: true,
          autoplay: {
            delay: 2500,
            disableOnInteraction: false
          },
          loop: true,
          loopedSlides: 5, //looped slides should be the same
          navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev'
          }
        },
        swiperOptionThumbs: {
          spaceBetween: 10,
          slidesPerView: 4,
          touchRatio: 0.2,
          loop: true,
          loopedSlides: 5, //looped slides should be the same
          slideToClickedSlide: true,
        }
      }
    },
    mounted() {
      this.$nextTick(() => {
        const swiperTop = this.$refs.swiperTop.swiper
        const swiperThumbs = this.$refs.swiperThumbs.swiper
        swiperTop.controller.control = swiperThumbs
        swiperThumbs.controller.control = swiperTop
      })
    }
  }
</script>

<style src="@/assets/styles/style.css">
/* @import '../../../frontend/src/assets/styles/style.css'; */
</style>