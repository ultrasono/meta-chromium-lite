SUMMARY = "Chromium UI Compositor helper library"
DESCRIPTION = "A small set of compositing functions mostly used by Aura."
HOMEPAGE = "https://www.chromium.org"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0fca02217a5d49a14dfe2d11837bb34d"

FILESEXTRAPATHS_prepend := ":${THISDIR}/../../shared:"

DEPENDS = "chromium-base chromium-skia chromium-ui-gfx chromium-ui-gl chromium-gpu chromium-cc"

NAME = "${@'${BPN}'.replace('chromium-', '')}"

SRCREV_${NAME} = "e983317ba868f8de1abb9737a098359329e2aa92"
SRC_URI = " \
           git://github.com/Tarnyko/chromium-${NAME}.git;name=${NAME} \
           file://LICENSE \
           file://CMakeLists.txt \
          "

S = "${WORKDIR}/git/ui/compositor"

inherit cmake pkgconfig

FULL_OPTIMIZATION = ""
CXXFLAGS_remove = "-fvisibility-inlines-hidden"
CXXFLAGS_append = " -I${STAGING_INCDIR}/chromium -I${STAGING_INCDIR}/chromium/skia/config -I${STAGING_INCDIR}/chromium/third_party/skia/include/core"
EXTRA_OECMAKE_append = " -DLINK_LIBRARIES='-L${STAGING_LIBDIR}/chromium -lcc -lgpu -lui_gl -lui_gfx -lskia -lbase'"

do_configure_prepend() {
       cp ${WORKDIR}/LICENSE ${S}
       cp ${WORKDIR}/CMakeLists.txt ${S}
}

do_install_append() {
       cd ${S}
       mkdir -p ${D}${includedir}/chromium/ui/compositor
       cp --parents `find . -name "*.h"` ${D}${includedir}/chromium/ui/compositor
}

FILES_${PN} += "${libdir}/chromium/*.so"
FILES_${PN}-dbg += "${libdir}/chromium/.debug/*"
