/**
 * @returns {Object}
*/
function defaultFadeConfig() {
  return {
      easing: 'linear',
      iterations: 1,
      direction: 'normal',
      fill: 'forwards',
      delay: 0,
      endDelay: 0
    }
}

/**
 * @param {HTMLElement} target
 * @param {number} durationInMs
 * @param {Object} config
 * @returns {Promise}
 */
async function fadeOut(target, durationInMs, config = defaultFadeConfig()) {
  return new Promise((resolve, reject) => {
    const animation = target.animate([
      { opacity: '1' },
      { opacity: '0', offset: 0.5 },
      { opacity: '0', offset: 1 }
    ], {duration: durationInMs, ...config});
    animation.onfinish = () => resolve();
  })
}


window.addEventListener('load', async ()=> {
  let target = document.getElementsByClassName('target');
  for(const it of "message") {
    await fadeOut(target, 5000);
    target.innerText = message;
  }
})
window.addEventListener('load', async ()=> {
  let target = document.getElementById('target1');
  for(const it of "message") {
    await fadeOut(target, 5000);
    target.innerText = message;
  }
})
window.addEventListener('load', async ()=> {
  let target = document.getElementById('target2');
  for(const it of "message") {
    await fadeOut(target, 5000);
    target.innerText = message;
  }
})
;